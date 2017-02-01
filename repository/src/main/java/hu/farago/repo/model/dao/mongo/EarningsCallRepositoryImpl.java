package hu.farago.repo.model.dao.mongo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import hu.farago.repo.model.entity.mongo.EarningsCall;
import hu.farago.repo.transformer.EarningsCallFlatSaver;

@Component
public class EarningsCallRepositoryImpl implements EarningsCallRepositoryCustom {

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private EarningsCallFlatSaver ecfSaver;

	@Override
	public <S extends EarningsCall> S saveFlat(S entity) {

		Assert.notNull(entity, "Entity must not be null!");
		String collectionName = mongoOperations.getCollectionName(EarningsCall.class);

		if (entity.id == null) {
			mongoOperations.insert(entity, collectionName);
		} else {
			mongoOperations.save(entity, collectionName);
		}

		ecfSaver.saveEarningsCallFlat(entity);
		return entity;
	}

	@Override
	public <S extends EarningsCall> List<S> saveFlat(Iterable<S> entities) {
		Assert.notNull(entities, "The given Iterable of entities not be null!");

		List<S> result = convertIterableToList(entities);
		boolean allNew = true;

		for (S entity : entities) {
			if (allNew && !isNew(entity)) {
				allNew = false;
			}
		}

		if (allNew) {
			mongoOperations.insertAll(result);
		} else {

			for (S entity : result) {
				saveFlat(entity);
			}
		}

		return result;
	}

	private <S extends EarningsCall> boolean isNew(S entity) {
		return entity.id == null || entity.id.longValue() == 0L;
	}
	
	private static <T> List<T> convertIterableToList(Iterable<T> entities) {

		if (entities instanceof List) {
			return (List<T>) entities;
		}

		int capacity = tryDetermineRealSizeOrReturn(entities, 10);

		if (capacity == 0 || entities == null) {
			return Collections.<T> emptyList();
		}

		List<T> list = new ArrayList<T>(capacity);
		for (T entity : entities) {
			list.add(entity);
		}

		return list;
	}

	private static int tryDetermineRealSizeOrReturn(Iterable<?> iterable, int defaultSize) {
		return iterable == null ? 0 : (iterable instanceof Collection) ? ((Collection<?>) iterable).size() : defaultSize;
	}


}
