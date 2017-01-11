package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QShortInterest is a Querydsl query type for ShortInterest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QShortInterest extends EntityPathBase<ShortInterest> {

    private static final long serialVersionUID = -489777305L;

    public static final QShortInterest shortInterest1 = new QShortInterest("shortInterest1");

    public final NumberPath<Double> avgDailyShareVolume = createNumber("avgDailyShareVolume", Double.class);

    public final NumberPath<Double> daysToCover = createNumber("daysToCover", Double.class);

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<org.joda.time.DateTime> settlementDate = createDateTime("settlementDate", org.joda.time.DateTime.class);

    public final NumberPath<Double> shortInterest = createNumber("shortInterest", Double.class);

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public QShortInterest(String variable) {
        super(ShortInterest.class, forVariable(variable));
    }

    public QShortInterest(Path<? extends ShortInterest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShortInterest(PathMetadata metadata) {
        super(ShortInterest.class, metadata);
    }

}

