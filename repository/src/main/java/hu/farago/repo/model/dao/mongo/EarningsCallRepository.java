package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.EarningsCall;

public interface EarningsCallRepository extends MongoRepository<EarningsCall, BigInteger> {
	
	List<EarningsCall> findByTradingSymbol(String tradingSymbol);
	
	List<EarningsCall> findByTradingSymbol(String tradingSymbol, Sort sort);
	
	EarningsCall findByUrl(String url);
	
	Long deleteByUrl(String url);
	
}