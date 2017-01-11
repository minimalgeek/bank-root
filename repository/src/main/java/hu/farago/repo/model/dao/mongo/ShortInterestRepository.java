package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.ShortInterest;

public interface ShortInterestRepository extends MongoRepository<ShortInterest, BigInteger> {

	List<ShortInterest> findByTradingSymbol(String symbol);
	
}
