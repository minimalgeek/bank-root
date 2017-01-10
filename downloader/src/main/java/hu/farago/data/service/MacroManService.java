package hu.farago.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hu.farago.data.macroman.MacroManDownloader;

@Controller
public class MacroManService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MacroManService.class);
	
	@Autowired
	private MacroManDownloader downloader;
	
	//@RequestMapping(value = "/downloadMacroMans", method = RequestMethod.GET)
	public void downloadMacroMans() {
		try {
			downloader.downloadAndSaveAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
}
