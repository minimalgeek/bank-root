package hu.farago.data.service;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import hu.farago.data.service.dto.URLSuccess;
import hu.farago.data.utils.AutomaticServiceErrorUtils;
import hu.farago.data.utils.URLUtils;
import hu.farago.data.zacks.ZacksFileUtils;
import hu.farago.data.zacks.ZacksStockQuoteDownloader;
import hu.farago.data.zacks.dto.ZacksData;
import hu.farago.repo.model.dao.mongo.ZacksEarningsCallDates2Repository;
import hu.farago.repo.model.entity.mongo.ZacksEarningsCallDates2;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError.AutomaticService;

@Controller
public class ZacksDownloadService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZacksDownloadService.class);

	@Value("#{'${zacks.urls}'.split(',')}")
	private List<String> zacksURLList;

	@Autowired
	private ZacksFileUtils zacksFileUtils;

	@Autowired
	private ZacksStockQuoteDownloader downloader;

	@Autowired
	private ZacksEarningsCallDates2Repository repository;

	@Autowired
	private AutomaticServiceErrorUtils aseu;

//	@RequestMapping(value = "/refreshAllReportDates", method = RequestMethod.GET, produces = {
//			MediaType.APPLICATION_JSON_VALUE })
	public List<URLSuccess> refreshAllReportDates() {
		LOGGER.info("refreshAllReportDates");

		List<URLSuccess> refreshedURLs = Lists.newArrayList();

		for (String url : zacksURLList) {
			try {
				String content = URLUtils.getContentOfURL(url);
				ZacksData zacksData = createZacksDataFromContent(content);
				zacksFileUtils.writeZacksDataToCSVFiles(zacksData);

				refreshedURLs.add(new URLSuccess(url, true));
			} catch (Exception ex) {
				LOGGER.error("Exception happened during URL content open or processing", ex);
				refreshedURLs.add(new URLSuccess(url, false));
				aseu.saveError(AutomaticService.ZACKS, ex.getMessage());
			}
		}

		return refreshedURLs;
	}

//	@RequestMapping(value = "/downloadAllZECD", method = RequestMethod.GET, produces = {
//			MediaType.APPLICATION_JSON_VALUE })
	public List<ZacksEarningsCallDates2> downloadAllZECD() {
		LOGGER.info("downloadAllZECD");

		try {
			List<ZacksEarningsCallDates2> list = downloader.downloadAllZECD();

			for (ZacksEarningsCallDates2 zecd2 : list) {

				if (nextReportDateIsInTheFuture(zecd2)) {
					List<ZacksEarningsCallDates2> oldRecords = repository.findByTradingSymbol(zecd2.tradingSymbol);
					for (ZacksEarningsCallDates2 record : oldRecords) {
						if (nextReportDateIsInTheFuture(record)) {
							repository.delete(record);
						}
					}
					repository.save(zecd2);
				}
			}

			return list;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.ZACKS, e.getMessage());
		}

		return null;
	}

	@Scheduled(cron = "0 0 6 * * ?")
	private void downloadAllZECDScheduled() {
		try {
			downloadAllZECD();
		} catch (Exception e) {
			aseu.saveError(AutomaticService.ZACKS, e.getMessage());
		}
	}

	private boolean nextReportDateIsInTheFuture(ZacksEarningsCallDates2 record) {
		return record.nextReportDate.isAfterNow()
				|| record.nextReportDate.withTimeAtStartOfDay().isEqual(DateTime.now().withTimeAtStartOfDay());
	}

	public ZacksData createZacksDataFromContent(String massContent)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(massContent, ZacksData.class);
	}

}
