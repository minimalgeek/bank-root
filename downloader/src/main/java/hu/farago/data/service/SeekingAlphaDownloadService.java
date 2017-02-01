package hu.farago.data.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Lists;

import hu.farago.data.seekingalpha.ProcessFirstNArticleParameter;
import hu.farago.data.seekingalpha.SeekingAlphaDownloader;
import hu.farago.data.seekingalpha.YahooStockDownloader;
import hu.farago.data.seekingalpha.bloomberg.EarningsCallFileImporter;
import hu.farago.data.utils.AutomaticServiceErrorUtils;
import hu.farago.data.zacks.ZacksECDateManager;
import hu.farago.repo.model.dao.mongo.EarningsCallRepository;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError.AutomaticService;
import hu.farago.repo.model.entity.mongo.EarningsCall;

@Controller
public class SeekingAlphaDownloadService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SeekingAlphaDownloadService.class);

	@Autowired
	private SeekingAlphaDownloader seekingAlphaDownloader;
	@Autowired
	private EarningsCallFileImporter fileImporter;
	@Autowired
	private EarningsCallRepository earningsCallRepository;
	// @Autowired
	// private InsiderDataRepository insiderDataRepo;
	// @Autowired
	// private EarningsCallAndInsiderDataAggregator aggregator;
	@Autowired
	private YahooStockDownloader stockDownloader;
	@Autowired
	private ZacksECDateManager manager;
	@Autowired
	private AutomaticServiceErrorUtils aseu;

//	@RequestMapping(value = "/collectEarningsCalls", method = RequestMethod.GET, produces = {
//			MediaType.APPLICATION_JSON_VALUE })
	public List<EarningsCallView> collectEarningsCalls() {
		LOGGER.info("collectEarningsCalls");

		List<EarningsCallView> ret = Lists.newArrayList();
		try {
			earningsCallRepository.deleteAll();
			for (int i = 0; i < seekingAlphaDownloader.pages(); i++) {
				Map<String, List<EarningsCall>> map = seekingAlphaDownloader.parseAll(i);

				for (Map.Entry<String, List<EarningsCall>> entry : map.entrySet()) {
					earningsCallRepository.save(entry.getValue());
					ret.addAll(entry.getValue().stream().map((ec) -> new EarningsCallView(ec)).collect(Collectors.toList()));
				}

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
		}

		return ret;
	}

	//@RequestMapping(value = "/importAllFiles", method = RequestMethod.GET)
	public List<EarningsCallView> importAllFiles() {
		LOGGER.info("importAllFiles");
		return fileImporter.importAll().stream().map((ec) -> new EarningsCallView(ec)).collect(Collectors.toList());
	}

	public List<EarningsCallView> collectEarningsCallsFor(String index) {
		LOGGER.info("collectEarningsCallsFor");

		try {
			List<EarningsCall> list = seekingAlphaDownloader.collectAllDataForIndex(index);

			// remove older entries
			earningsCallRepository.delete(earningsCallRepository.findByTradingSymbol(index));
			earningsCallRepository.save(list);
			
			return list.stream().map(e -> new EarningsCallView(e)).collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.SEEKING_ALPHA, "Error in url: " + e.getMessage());
			return Lists.newArrayList();
		}
	}

	public List<String> processQAndAAndAddStockData() {
		LOGGER.info("processQAndAAndAddStockData");

		List<String> ret = Lists.newArrayList();
		try {
			for (String index : seekingAlphaDownloader.getIndexes()) {
				List<EarningsCall> calls = earningsCallRepository.findByTradingSymbol(index);

				for (EarningsCall call : calls) {
					if (call.tone != null && call.stockData == null) {
						seekingAlphaDownloader.retrieveRelevantQAndAPartAndProcessTone(call);
						stockDownloader.addStockData(call);
					}
				}

				earningsCallRepository.save(calls);
				LOGGER.info(index + " processed");
				ret.add(index);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
		}

		return ret;
	}

	public List<EarningsCallView> collectLastNTranscripts(int numberOfTranscriptsNeeded) {
		LOGGER.info("collectLastNTranscripts");

		List<EarningsCallView> ret = Lists.newLinkedList();

		for (String indexName : seekingAlphaDownloader.getIndexes()) {
			ret.addAll(collectLastNTranscriptsFor(numberOfTranscriptsNeeded, indexName));
		}

		return ret;
	}
	
	public List<EarningsCallView> collectLastNTranscriptsFor(int numberOfTranscriptsNeeded, String ticker) {
		LOGGER.info("collectLastNTranscriptsFor");

		List<EarningsCallView> ret = Lists.newLinkedList();

		try {
			List<EarningsCall> calls = seekingAlphaDownloader.collectLatestNForIndex(
					new ProcessFirstNArticleParameter(ticker, numberOfTranscriptsNeeded));

			for (EarningsCall call : calls) {
				if (earningsCallRepository.findByUrl(call.url) == null) {
					LOGGER.info("New earnings call found: " + call.url);
					earningsCallRepository.save(call);
					ret.add(new EarningsCallView(call));
				}
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
		}

		return ret;
	}

//	@RequestMapping(value = "/lookForTranscripts", method = RequestMethod.GET)
	public void lookForTranscripts() {
		LOGGER.info("lookForTranscripts");
		manager.lookForTranscripts();
	}

	// every day at midnight
	@Scheduled(cron = "0 0 12 * * ?")
	public void lookForTranscriptsScheduled() {
		try {
			lookForTranscripts();
		} catch (Exception e) {
			aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
		}
	}

	public class EarningsCallView {
		
		private String tradingSymbol;
		private String url;
		private DateTime publishDate;
		
		public EarningsCallView(EarningsCall ec) {
			this.tradingSymbol = ec.tradingSymbol;
			this.url = ec.url;
			this.publishDate = ec.publishDate;
		}
		
		public DateTime getPublishDate() {
			return publishDate;
		}
		
		public String getTradingSymbol() {
			return tradingSymbol;
		}
		
		public String getUrl() {
			return url;
		}
		
	}
}
