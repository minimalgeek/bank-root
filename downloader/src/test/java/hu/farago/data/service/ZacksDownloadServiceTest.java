package hu.farago.data.service;

import hu.farago.data.AbstractRootTest;
import hu.farago.data.service.ZacksDownloadService;
import hu.farago.data.utils.URLUtils;
import hu.farago.data.zacks.dto.ZacksData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tika.exception.TikaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;

public class ZacksDownloadServiceTest extends AbstractRootTest {

	@Autowired
	private ZacksDownloadService dataSync;
	
	@Value("${zacks.url.test}")
	private String testURL;
	@Value("${zacks.path}")
	private String zacksPath;

	private File zacksDirectory;
	
	private static final String SAMPLE_RESPONSE_PATH = "sample_response.txt";
	private String sampleResponse;
	
	@Before
	public void setUp() throws Exception {
		
		InputStream stream = Thread.currentThread().getContextClassLoader()
			    .getResourceAsStream(SAMPLE_RESPONSE_PATH);
		
		sampleResponse = IOUtils.toString(stream, "UTF-8");
		zacksDirectory = new File(zacksPath);
	}
	
	@Test
	public void getContentForURLTest() throws IOException, SAXException, TikaException {
		String content = URLUtils.getContentOfURL(testURL);
		Assert.assertNotNull(content);
		Assert.assertFalse(StringUtils.isEmpty(content));
	}
	
	@Test
	public void createZacksDataFromContentTest() throws IOException {
		ZacksData zacksData = dataSync.createZacksDataFromContent(sampleResponse);
		Assert.assertEquals(4, zacksData.getData().size());
	}
	
	@Test
	public void refreshAllReportDatesTest() throws IOException {
		dataSync.refreshAllReportDates();
		FileUtils.cleanDirectory(zacksDirectory);
	}
}
