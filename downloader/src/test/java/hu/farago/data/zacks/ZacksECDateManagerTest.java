package hu.farago.data.zacks;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import hu.farago.data.AbstractRootTest;
import hu.farago.data.seekingalpha.EarningsCallCollectFilter;
import hu.farago.data.seekingalpha.SeekingAlphaDownloader;
import hu.farago.data.zacks.ZacksECDateManager.ManagerParameterObject;
import hu.farago.repo.model.dao.mongo.ZacksEarningsCallDatesRepository;
import hu.farago.repo.model.entity.mongo.EarningsCall;

public class ZacksECDateManagerTest extends AbstractRootTest {
	
	private static final String AAPL = "AAPL";
	private static final String IBM = "IBM";

	@Autowired
	private ZacksECDateManager manager;
	@Autowired
	private ZacksEarningsCallDatesRepository repository;
	@Autowired
	private SeekingAlphaDownloader downloader;
	
	@Resource
    private ThreadPoolTaskScheduler taskScheduler;
	
	private ManagerParameterObject sample;
	private ManagerParameterObject updatedSample;
	private ManagerParameterObject earlyReport;
	private ManagerParameterObject ibmSample;
	
	@Before
	public void before() {
		repository.deleteAll();
		
		sample = new ManagerParameterObject();
		sample.nextReportDate = createInUTC(2016, 1, 5);
		sample.tradingSymbol = AAPL;
		
		updatedSample = new ManagerParameterObject();
		updatedSample.nextReportDate = createInUTC(2016, 1, 7);
		updatedSample.tradingSymbol = AAPL;
		
		earlyReport = new ManagerParameterObject();
		earlyReport.nextReportDate = createInUTC(2015, 10, 10);
		earlyReport.tradingSymbol = AAPL;
		
		ibmSample = new ManagerParameterObject();
		ibmSample.nextReportDate = createInUTC(2016, 1, 4);
		ibmSample.tradingSymbol = IBM;
		
		DateTimeUtils.setCurrentMillisFixed(
				sample.nextReportDate.minusDays(2).getMillis()); // 2016. 01. 03.
	}

	@After
	public void after() {
		repository.deleteAll();
		DateTimeUtils.setCurrentMillisSystem();
	}


	@Test
	public void testAddDate() {
		
		manager.addDate(sample);
		manager.addDate(sample); // add the same!
		
		assertThat(repository.count(), equalTo(1L));
	}
	
	@Test
	public void testAddMoreDate() {
		
		manager.addDate(earlyReport);
		manager.addDate(sample);
		
		assertThat(repository.count(), equalTo(2L));
		
	}

	@Test
	public void testOverrideDate() {
		manager.addDate(sample); // current report
		manager.addDate(sample); // again :)
		manager.addDate(earlyReport); // early report
		manager.overrideDate(updatedSample); // refreshed current report
		
		assertThat(repository.count(), equalTo(2L));
	}

	@Test
	public void testLookForTranscripts() throws InterruptedException {
		manager.addDate(sample);
		manager.addDate(ibmSample);
		manager.addDate(earlyReport);
		
		manager.lookForTranscripts();
		
		//assertThat(taskScheduler.getActiveCount(), greaterThan(0));
	}

	@Test
	public void testCollectLatestForIndex() throws Exception {
		EarningsCall call = downloader.collectLatestForIndex(new EarningsCallCollectFilter(AAPL));
		
		assertNotNull(call);
	}
	
	private DateTime createInSystem(int year, int month, int day) {
		return new DateTime(year, month, day, 0, 0).withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.getDefault());
	}
	
	private DateTime createInUTC(int year, int month, int day) {
		return new DateTime(year, month, day, 0, 0).withZoneRetainFields(DateTimeZone.UTC);
	}
}
