package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.ZacksEarningsCallDates;

public interface ZacksEarningsCallDatesRepository extends MongoRepository<ZacksEarningsCallDates, BigInteger> {

	List<ZacksEarningsCallDates> findByTradingSymbol(String symbol);
	
	ZacksEarningsCallDates findByTradingSymbolAndNextReportDate(String symbol, DateTime nextReportDate);
	
	List<ZacksEarningsCallDates> findBySeekingAlphaCheckDateIn(List<DateTime> dates);
	
}
