package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import hu.farago.repo.model.entity.mongo.Forex;

public interface ForexRepository extends MongoRepository<Forex, BigInteger>, QueryDslPredicateExecutor<Forex> {

	Forex findFirstBySymbolOrderByTickDateDesc(String symbol);
	
}