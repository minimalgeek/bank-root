package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.SAndPIndex;

public interface SAndPIndexRepository extends MongoRepository<SAndPIndex, BigInteger> {

	SAndPIndex findByTradingSymbol(String symbol);
	
}
