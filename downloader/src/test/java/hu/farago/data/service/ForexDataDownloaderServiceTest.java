package hu.farago.data.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.farago.data.AbstractRootTest;

public class ForexDataDownloaderServiceTest extends AbstractRootTest {

	@Autowired
	private ForexDataDownloaderService service;

	@Test
	public void downloadAllTest() {
		service.downloadAll();
	}
	
	@Test
	public void downloadMissingTest() {
		service.downloadMissing();
	}

}
