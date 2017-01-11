package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.InsiderData;

public interface InsiderDataRepository extends MongoRepository<InsiderData, BigInteger> {
	
	List<InsiderData> findByIssuerTradingSymbol(String symbol);
	
}