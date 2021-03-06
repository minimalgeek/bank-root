package hu.farago.data.zacks;

import hu.farago.data.seekingalpha.EarningsCallCollectFilter;
import hu.farago.data.seekingalpha.SeekingAlphaDownloader;
import hu.farago.data.utils.AutomaticServiceErrorUtils;
import hu.farago.repo.model.dao.mongo.EarningsCallRepository;
import hu.farago.repo.model.dao.mongo.ZacksEarningsCallDatesRepository;
import hu.farago.repo.model.entity.mongo.EarningsCall;
import hu.farago.repo.model.entity.mongo.ZacksEarningsCallDates;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError.AutomaticService;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Component
public class ZacksECDateManager {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ZacksECDateManager.class);

	@Autowired
	private ZacksEarningsCallDatesRepository zacksRepository;
	@Autowired
	private EarningsCallRepository ecRepository;
	@Autowired
	private SeekingAlphaDownloader downloader;
	@Resource
    private ThreadPoolTaskScheduler taskScheduler;
	@Autowired
	private AutomaticServiceErrorUtils aseu;
	
	public void addDate(ManagerParameterObject obj) {
		ZacksEarningsCallDates dateToStore = createMongoObjectFromParameterObject(obj);
		
		List<ZacksEarningsCallDates> listOfCallDates = zacksRepository.findByTradingSymbol(dateToStore.tradingSymbol);
		ZacksEarningsCallDates foundCallDate = Iterables.tryFind(listOfCallDates, findByDatePredicate(dateToStore.nextReportDateInLocal())).orNull();
		
		if (foundCallDate == null) {
			zacksRepository.save(dateToStore);
			LOGGER.info("Date saved: " + dateToStore.toString());
		}
	}
	
	public void overrideDate(ManagerParameterObject obj) {
		ZacksEarningsCallDates dateToStore = createMongoObjectFromParameterObject(obj);
		
		List<ZacksEarningsCallDates> listOfCallDates = zacksRepository.findByTradingSymbol(dateToStore.tradingSymbol);
		
		if (!CollectionUtils.isEmpty(listOfCallDates)) {
			DateTime maxDate = listOfCallDates.stream().map(u -> u.nextReportDate).max(DateTime::compareTo).get();
			ZacksEarningsCallDates foundCallDate = Iterables.tryFind(listOfCallDates, findByDatePredicate(maxDate)).orNull();
			
			if (foundCallDate != null) {
				zacksRepository.delete(foundCallDate);
				LOGGER.info("Report date changed: " + foundCallDate.toString() + 
							" to " + dateToStore.nextReportDate.toString());
			}
		}
		
		zacksRepository.save(dateToStore);
	}

	public void lookForTranscripts() {
		DateTime to = DateTime.now().withZone(DateTimeZone.UTC).withTimeAtStartOfDay();
		DateTime from = to.minusDays(4);
		List<ZacksEarningsCallDates> listOfCallDates = zacksRepository.findByNextReportDateBetween(from, to);
		
		for (ZacksEarningsCallDates zecd : listOfCallDates) {
			if (zecd.foundEarningsCallId == null) {
				searchForEarningsCall(zecd);
			}
		}
	}
	
	// private functions
	
	private void searchForEarningsCall(ZacksEarningsCallDates zecd) {
		try {
			EarningsCall call = downloader.collectLatestForIndex(new EarningsCallCollectFilter(zecd.tradingSymbol));
			if (call != null && call.publishDate.isAfter(zecd.nextReportDate.minusDays(5)) && call.publishDate.isBefore(zecd.nextReportDate.plusDays(5))) {
				EarningsCall olderCall = ecRepository.findByUrl(call.url);
				if (olderCall != null) {
					zecd.foundEarningsCallId = olderCall.id;
					zacksRepository.save(zecd);
				} else {
					call.zacksEarningsCallDate = zecd.nextReportDate;
					call = ecRepository.saveFlat(call);
					
					zecd.foundEarningsCallId = call.id;
					zacksRepository.save(zecd);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.ZACKS, e.getMessage());
		}
	}
	
	private ZacksEarningsCallDates createMongoObjectFromParameterObject(
			ManagerParameterObject obj) {
		ZacksEarningsCallDates dateToStore = new ZacksEarningsCallDates();
		
		dateToStore.nextReportDate = obj.nextReportDate;
		dateToStore.tradingSymbol = obj.tradingSymbol;
		
		return dateToStore;
	}
	
	private Predicate<ZacksEarningsCallDates> findByDatePredicate(
			DateTime dt) {
		return new Predicate<ZacksEarningsCallDates>() {
			@Override
			public boolean apply(ZacksEarningsCallDates input) {
				return input.nextReportDate.equals(dt);
			}
		};
	}

	// static objects
	
	public static class ManagerParameterObject {
		public String tradingSymbol;
		public DateTime nextReportDate;
		
		public ManagerParameterObject() {
			super();
		}

		public ManagerParameterObject(String tradingSymbol,
				DateTime nextReportDate) {
			super();
			this.tradingSymbol = tradingSymbol;
			this.nextReportDate = nextReportDate;
		}
	}
}
