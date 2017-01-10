package hu.farago.mongo.service;

import hu.farago.mongo.AbstractRootTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MongoExportServiceTest extends AbstractRootTest {

	@Autowired
	private MongoExportService mongoExportService;
	
	@Value("${exporter.path}")
	private String exportPath;
	
	@Value("${exporter.earningsCallPath}")
	private String earningsCallPath;

	@Test
	public void testExportEarningsCall() throws IOException {
		File dir = new File(exportPath + earningsCallPath);
		mongoExportService.exportEarningsCall();
		
		assertEquals(0, FileUtils.sizeOfDirectory(dir));
		FileUtils.cleanDirectory(dir);
	}

}
