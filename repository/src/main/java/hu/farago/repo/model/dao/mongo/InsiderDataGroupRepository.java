package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.InsiderDataGroup;

public interface InsiderDataGroupRepository extends MongoRepository<InsiderDataGroup, BigInteger> {
	
	List<InsiderDataGroup> findByIssuerTradingSymbol(String symbol);
	
}