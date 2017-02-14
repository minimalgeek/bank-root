package hu.farago.data.seekingalpha;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import hu.farago.data.api.DataDownloader;
import hu.farago.data.api.WordProcessor;
import hu.farago.data.utils.AutomaticServiceErrorUtils;
import hu.farago.data.utils.URLUtils;
import hu.farago.repo.model.dao.mongo.EarningsCallRepository;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError.AutomaticService;
import hu.farago.repo.model.entity.mongo.EarningsCall;
import hu.farago.repo.utils.DateTimeUtils;

@Component
public class SeekingAlphaDownloader extends DataDownloader<EarningsCall> {

	public static final String QUESTION_AND_ANSWER = "(?i)question-and-answer";
	public static final String QUESTION_AND_ANSWER_2 = "(?i)question and answer";
	public static final String COPYRIGHT_POLICY = "(?i)copyright policy";
	public static final String EARNINGS_CALL_TRANSCRIPT = "earnings call transcript";
	private static final Integer IMPORT_TRESHOLD = 3;

	private static final Logger LOGGER = LoggerFactory.getLogger(SeekingAlphaDownloader.class);

	@Value("${seekingalpha.filePath}")
	private String filePath;
	@Value("${seekingalpha.articleUrlBase}")
	private String articleUrlBase;
	@Value("${seekingalpha.urlBase}")
	private String urlBase;
	@Value("${seekingalpha.urlMiddle}")
	private String urlMiddle;
	@Value("${seekingalpha.delay}")
	private Integer delay;

	@Autowired
	private WordProcessor simpleWordProcessor;

	@Autowired
	private ToneCalculator toneCalculator;

	@Autowired
	private AutomaticServiceErrorUtils aseu;
	
	@Autowired
	private EarningsCallRepository ecr;

	@PostConstruct
	private void readFile() {
		readFileFromPathAndFillIndexes(filePath);
	}

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}
	
	@Override
	protected boolean shouldBeSkipped(String index) {
		return ecr.countByTradingSymbol(index) > IMPORT_TRESHOLD;
	}

	// http://seekingalpha.com/symbol/ACXM/earnings/more_transcripts?page=1
	@Override
	protected String buildUrl(String index, int pageIndex) {
		StringBuilder builder = new StringBuilder(urlBase);
		builder.append(index);
		builder.append(urlMiddle);
		builder.append(pageIndex);
		return builder.toString();
	}

	@Override
	protected boolean notLastPage(String siteContent) {
		return !siteContent.contains("\"count\":0");
	}

	@Override
	protected List<EarningsCall> processDocument(String index, Document document) {

		// built in slowdown because of HTTP 429
		try {
			TimeUnit.MILLISECONDS.sleep(delay + (long) (Math.random() * 4000));
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		ecr.deleteByTradingSymbol(index);

		List<EarningsCall> ret = Lists.newArrayList();

		Elements htmlData = extractHtmlData(document);
		try {
			for (Element earningsCallArticle : htmlData) {
				if (elementIsLegalTranscript(earningsCallArticle)) {
					try {
						// built in slowdown because of HTTP 429
						TimeUnit.MILLISECONDS.sleep(delay + (long) (Math.random() * 4000));

						EarningsCall call = createEarningsCall(earningsCallArticle, index);

						if (call.words.size() > 200) {
							// it is probably a real earnings call, not only a
							// link to some audio shit
							processTone(call);
							retrieveRelevantQAndAPartAndProcessTone(call);
						}

						ret.add(call);
					} catch (Exception e) {
						LOGGER.error("Failed to process: (" + earningsCallArticle + ")", e);
						aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed to process the index JSON for: (" + index + ")", e);
			aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
		}

		return ret;
	}

	private Elements extractHtmlData(Document document) {
		Element container = document.getElementsByTag("body").first();
		return container.children();
	}

	public EarningsCall collectLatestForIndex(EarningsCallCollectFilter parameterObject) throws Exception {
		parameterObject.count = 1;
		List<EarningsCall> lst = collectLatestNForIndex(parameterObject);
		return CollectionUtils.isEmpty(lst) ? null : lst.get(0);
	}

	public List<EarningsCall> collectLatestNForIndex(EarningsCallCollectFilter parameterObject) throws Exception {
		LOGGER.info("Collect latest for index: " + parameterObject.index);
		String urlStr = buildUrl(parameterObject.index, 0);
		String siteContent = URLUtils.getHTMLContentOfURL(urlStr);
		Document document = Jsoup.parse(siteContent);

		parameterObject.document = document;

		return processFirstNArticle(parameterObject);
	}

	private void processTone(EarningsCall call) {
		call.tone = toneCalculator.getToneOf(call.words);
		call.hTone = toneCalculator.getHToneOf(call.words);
	}

	private boolean elementIsLegalTranscript(Element earningsCallArticle) {
		// boolean isQP = earningsCallArticle.hasAttr("sasource") &&
		// validSASource(earningsCallArticle.attr("sasource"));
		String linkText = earningsCallArticle.text();
		return linkText.toLowerCase().contains(EARNINGS_CALL_TRANSCRIPT);
	}

	// private boolean validSASource(String src) {
	// return StringUtils.equals(src, "qp_analysis") || StringUtils.equals(src,
	// "qp_transcripts");
	// }

	private EarningsCall createEarningsCall(Element dataRow, String index) throws Exception {

		String href = dataRow.getElementsByTag("a").get(1).attr("href").replaceAll("\\\\\"", "");
		String articleUrl = articleUrlBase + href;
		LOGGER.info("Processing: " + articleUrl);

		String articleHTMLContent = URLUtils.getHTMLContentOfURL(articleUrl);
		Document doc = Jsoup.parse(articleHTMLContent);
		String articleBody = URLUtils.getContentOfHTMLContent(doc.getElementById("a-body").text());

		EarningsCall data = new EarningsCall();
		data.url = articleUrl;
		data.tradingSymbol = index;
		data.rawText = articleBody;
		for (Element dateTime : doc.getElementsByTag("time")) {
			if (StringUtils.equals(dateTime.attr("itemprop"), "dateModified")) {
				data.publishDate = parseDate(dateTime);
				break;
			}
		}
		data.words = simpleWordProcessor.parseArticlePlainTextAndBuildMapOfWords(data.rawText);

		return data;
	}

	public void retrieveRelevantQAndAPartAndProcessTone(EarningsCall earningsCall) {
		String[] qAndAParts = earningsCall.rawText.split(QUESTION_AND_ANSWER);
		if (qAndAParts.length >= 2) {
			processQAndA(earningsCall, qAndAParts);
			return;
		}

		qAndAParts = earningsCall.rawText.split(QUESTION_AND_ANSWER_2);
		if (qAndAParts.length >= 2) {
			processQAndA(earningsCall, qAndAParts);
			return;
		}

	}

	private List<EarningsCall> processFirstNArticle(EarningsCallCollectFilter parameterObject) {
		int processed = 0;
		List<EarningsCall> processedCalls = new ArrayList<>();

		Elements htmlData = extractHtmlData(parameterObject.document);
		try {
			for (Element earningsCallArticle : htmlData) {
				if (elementIsLegalTranscript(earningsCallArticle)) {
					try {
						EarningsCall call = createEarningsCall(earningsCallArticle, parameterObject.index);

						if (call.words.size() > 200) {
							// it is probably a real earnings call, not only a
							// link to some audio shit
							processTone(call);
							retrieveRelevantQAndAPartAndProcessTone(call);
							processedCalls.add(call);
							processed++;
						}
					} catch (Exception e) {
						LOGGER.error("Failed to process: (" + earningsCallArticle + ")", e);
						aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
					}
				}

				if (parameterObject.count > 0 && processed == parameterObject.count) {
					return processedCalls;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed to process the index JSON for: (" + parameterObject.index + ")", e);
			aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
		}

		return processedCalls;
	}

	// private ArrayList<String> getArticleList(String htmlData) throws
	// IOException, SAXException, TikaException {
	// return
	// Lists.newArrayList(URLUtils.getContentOfHTMLContent(htmlData).split("<li>"));
	// }

	private void processQAndA(EarningsCall earningsCall, String[] qAndAParts) {
		String qAndA = qAndAParts[qAndAParts.length - 1];
		qAndA = qAndA.split(COPYRIGHT_POLICY)[0];

		earningsCall.qAndAText = qAndA;
		earningsCall.qAndAWords = simpleWordProcessor.parseArticlePlainTextAndBuildMapOfWords(earningsCall.qAndAText);

		if (earningsCall.qAndAWords.size() > 50) {
			// it is probably a real earnings call, not only a link to some
			// audio shit
			earningsCall.qAndATone = toneCalculator.getToneOf(earningsCall.qAndAWords);
			earningsCall.qAndAHTone = toneCalculator.getHToneOf(earningsCall.qAndAWords);
		}
	}

	private DateTime parseDate(Element dateTime) {
		try {
			return DateTimeUtils.parseToYYYYMMDD_HHmmss_ZONE_UTC(dateTime.attr("datetime"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
			return null;
		}
	}

}
