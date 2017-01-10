package hu.farago.data.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.farago.data.AbstractRootTest;

public class InsiderTradingDownloadServiceTest extends AbstractRootTest {
		
	@Autowired
	private InsiderTradingDownloadService downloadService;
	
	@Test
	public void downloadAndWriteTest() throws Exception {
		downloadService.collectContent();
	}

}
