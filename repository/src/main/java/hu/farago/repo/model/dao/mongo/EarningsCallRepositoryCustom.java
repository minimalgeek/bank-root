package hu.farago.repo.model.dao.mongo;

import java.util.List;

import hu.farago.repo.model.entity.mongo.EarningsCall;

public interface EarningsCallRepositoryCustom {
	<S extends EarningsCall> S saveFlat(S entity);
	<S extends EarningsCall> List<S> saveFlat(Iterable<S> entites);
}