package hu.farago.data.zacks;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tika.exception.TikaException;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

import hu.farago.data.utils.AutomaticServiceErrorUtils;
import hu.farago.data.utils.URLUtils;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError.AutomaticService;
import hu.farago.repo.model.entity.mongo.ZacksEarningsCallDates2;

@Component
public class ZacksStockQuoteDownloader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZacksStockQuoteDownloader.class);

	private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yy");

	private static final String EARNINGS_DATE = "Exp Earnings Date";

	private Pattern pattern = Pattern.compile("\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}");

	@Value("${zacks.stockQuote.urlBase}")
	private String zacksStockQuoteUrlBase;

	@Value("${seekingalpha.filePath}")
	private String seekingalphaFilePath;
	
	@Autowired
	private AutomaticServiceErrorUtils aseu;

	public List<ZacksEarningsCallDates2> downloadAllZECD() throws Exception {
		List<ZacksEarningsCallDates2> ret = Lists.newArrayList();

		for (String tradingSymbol : loadIndexList()) {
			ZacksEarningsCallDates2 zecd2 = new ZacksEarningsCallDates2();
			zecd2.tradingSymbol = tradingSymbol;

			try {
				Document document = downloadHTMLDocument(zecd2);
				Element dateContainerTD = findDateContainer(document);

				if (dateContainerTD != null) {
					Matcher matcher = pattern.matcher(dateContainerTD.text());
					if (matcher.find()) {
						zecd2.nextReportDate = formatter.parseDateTime(matcher.group(0))
								.withZoneRetainFields(DateTimeZone.UTC);
					}
				}

				zecd2.success = zecd2.nextReportDate != null;
			} catch (Exception e) {
				aseu.saveError(AutomaticService.ZACKS, zecd2.url);
				zecd2.success = false;
			}

			ret.add(zecd2);
		}
		return ret;
	}

	private Document downloadHTMLDocument(ZacksEarningsCallDates2 zecd2) throws IOException, SAXException, TikaException {
		String url = zacksStockQuoteUrlBase + zecd2.tradingSymbol;
		zecd2.url = url;
		LOGGER.info("Downloading zacks stock quote: " + url);
		Document document = URLUtils.getDocumentContentOfURL(url);
		return document;
	}

	private Element findDateContainer(Document document) {
		Element container = document.getElementById("stock_key_earnings");
		Element dateContainerTD = null;
		if (container != null) {
			Elements tds = container.getElementsByTag("td");
			for (Element td : tds) {
				if (td.text().equals(EARNINGS_DATE)) {
					dateContainerTD = td.nextElementSibling();
				}
			}
		}
		return dateContainerTD;
	}

	private List<String> loadIndexList() {

		File file = new File(seekingalphaFilePath);
		List<String> indexes = Lists.newArrayList();

		try {
			indexes = FileUtils.readLines(file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.ZACKS, "Error reading file: " + seekingalphaFilePath);
		}

		return indexes;
	}

}
