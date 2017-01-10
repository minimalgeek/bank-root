package hu.farago.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hu.farago.data.model.dao.mongo.OilReportRepository;
import hu.farago.data.oilreport.OilReportDownloader;

@Controller
public class OilReportService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OilReportService.class);
	
	@Autowired
	private OilReportDownloader downloader;
	@Autowired
	private OilReportRepository repositroy;
	
	//@RequestMapping(value = "/downloadOilReports", method = RequestMethod.GET)
	public void downloadOilReports() {
		try {
			downloader.downloadAndSaveAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
