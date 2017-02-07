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

import hu.farago.data.seekingalpha.EarningsCallCollectFilter;
import hu.farago.data.seekingalpha.SeekingAlphaDownloader;
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
	@Autowired
	private ZacksECDateManager manager;
	@Autowired
	private AutomaticServiceErrorUtils aseu;

	public List<EarningsCallView> collectEarningsCalls(EarningsCallCollectFilter parameterObject) {
		LOGGER.info("collectEarningsCalls");

		if (parameterObject == null) {
			return collectAllEarningsCalls();
		} else {
			return collectEarningsCallsByFilter(parameterObject);
		}

	}

	private List<EarningsCallView> collectAllEarningsCalls() {
		LOGGER.info("collectAllEarningsCalls");

		List<EarningsCallView> ret = Lists.newArrayList();
		try {
			earningsCallRepository.deleteAll();
			for (int i = 0; i < seekingAlphaDownloader.pages(); i++) {
				Map<String, List<EarningsCall>> map = seekingAlphaDownloader.parseAll(i);

				for (Map.Entry<String, List<EarningsCall>> entry : map.entrySet()) {
					earningsCallRepository.saveFlat(entry.getValue());
					ret.addAll(entry.getValue().stream().map((ec) -> new EarningsCallView(ec))
							.collect(Collectors.toList()));
				}

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
		}

		return ret;
	}

	/*
	 * LOGGER.info("collectLastNTranscripts");
	 * 
	 * List<EarningsCallView> ret = Lists.newLinkedList();
	 * 
	 * 
	 * 
	 * return ret;
	 */

	private List<EarningsCallView> collectEarningsCallsByFilter(EarningsCallCollectFilter parameterObject) {
		LOGGER.info("collectEarningsCallsByFilter");

		List<EarningsCallView> ret = Lists.newLinkedList();
		if (parameterObject.index == null) {
			for (String indexName : seekingAlphaDownloader.getIndexes()) {
				parameterObject.index = indexName;
				ret.addAll(collectEarningsCallsByFilter(parameterObject));
			}
		} else {
			try {
				List<EarningsCall> calls = seekingAlphaDownloader.collectLatestNForIndex(parameterObject);

				for (EarningsCall call : calls) {
					if (earningsCallRepository.findByUrl(call.url) == null) {
						LOGGER.info("New earnings call found: " + call.url);
						earningsCallRepository.saveFlat(call);
						ret.add(new EarningsCallView(call));
					}
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				aseu.saveError(AutomaticService.SEEKING_ALPHA, e.getMessage());
			}

		}
		return ret;
	}

	public List<EarningsCallView> importAllFiles() {
		LOGGER.info("importAllFiles");
		return fileImporter.importAll().stream().map((ec) -> new EarningsCallView(ec)).collect(Collectors.toList());
	}

	public void lookForTranscripts() {
		LOGGER.info("lookForTranscripts");
		manager.lookForTranscripts();
	}

	// runs every day at 15:00 in eastern american timezone
	@Scheduled(cron = "0 0 15 * * ?", zone="America/New_York")
	// runs every day at 14:30 in eastern american timezone
	@Scheduled(cron = "0 30 14 * * ?", zone="America/New_York")
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
