package hu.farago.repo.model.dao.mongo;

import java.util.List;

import hu.farago.repo.model.entity.mongo.EarningsCall;

public interface EarningsCallRepositoryCustom {
	<S extends EarningsCall> S save(S entity);
	<S extends EarningsCall> List<S> save(Iterable<S> entites);
}