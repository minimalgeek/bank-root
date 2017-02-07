package hu.farago.data.seekingalpha;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.farago.data.AbstractRootTest;
import hu.farago.repo.model.entity.mongo.EarningsCall;

public class SeekingAlphaDownloaderTopNTest extends AbstractRootTest {

	private static final String AAPL = "AAPL";
	
	@Autowired
	private SeekingAlphaDownloader downloader;
	
	@Test
	public void testCollectLatestNForIndex() throws Exception {
		List<EarningsCall> calls = downloader.collectLatestNForIndex(new EarningsCallCollectFilter(AAPL, 4));
		
		assertEquals(4, calls.size());
	}
	
}
