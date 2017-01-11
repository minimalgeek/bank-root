package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QForex is a Querydsl query type for Forex
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QForex extends EntityPathBase<Forex> {

    private static final long serialVersionUID = 1552482269L;

    public static final QForex forex = new QForex("forex");

    public final NumberPath<Double> close = createNumber("close", Double.class);

    public final NumberPath<Double> high = createNumber("high", Double.class);

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final NumberPath<Double> low = createNumber("low", Double.class);

    public final NumberPath<Double> open = createNumber("open", Double.class);

    public final StringPath symbol = createString("symbol");

    public final DateTimePath<org.joda.time.DateTime> tickDate = createDateTime("tickDate", org.joda.time.DateTime.class);

    public final NumberPath<Double> volume = createNumber("volume", Double.class);

    public QForex(String variable) {
        super(Forex.class, forVariable(variable));
    }

    public QForex(Path<? extends Forex> path) {
        super(path.getType(), path.getMetadata());
    }

    public QForex(PathMetadata metadata) {
        super(Forex.class, metadata);
    }

}

