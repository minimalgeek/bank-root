package hu.farago.repo.model.dao.mongo;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.farago.repo.model.entity.mongo.AutomaticServiceError;

public interface AutomaticServiceErrorRepository extends MongoRepository<AutomaticServiceError, BigInteger> {

}
