package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.Edgar10QData;

public interface Edgar10KDataRepository extends MongoRepository<Edgar10QData, BigInteger> {
	
	List<Edgar10QData> findByTradingSymbol(String symbol);
	
}