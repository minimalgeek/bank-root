package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.EdgarData;

public interface EdgarDataRepository extends MongoRepository<EdgarData, BigInteger> {
	
	List<EdgarData> findByTradingSymbol(String symbol);
	
}