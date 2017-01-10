package hu.farago.data.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QZacksEarningsCallDates is a Querydsl query type for ZacksEarningsCallDates
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QZacksEarningsCallDates extends EntityPathBase<ZacksEarningsCallDates> {

    private static final long serialVersionUID = -1171508303L;

    public static final QZacksEarningsCallDates zacksEarningsCallDates = new QZacksEarningsCallDates("zacksEarningsCallDates");

    public final NumberPath<java.math.BigInteger> foundEarningsCallId = createNumber("foundEarningsCallId", java.math.BigInteger.class);

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<org.joda.time.DateTime> nextReportDate = createDateTime("nextReportDate", org.joda.time.DateTime.class);

    public final ListPath<org.joda.time.DateTime, DateTimePath<org.joda.time.DateTime>> seekingAlphaCheckDate = this.<org.joda.time.DateTime, DateTimePath<org.joda.time.DateTime>>createList("seekingAlphaCheckDate", org.joda.time.DateTime.class, DateTimePath.class, PathInits.DIRECT2);

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public QZacksEarningsCallDates(String variable) {
        super(ZacksEarningsCallDates.class, forVariable(variable));
    }

    public QZacksEarningsCallDates(Path<? extends ZacksEarningsCallDates> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZacksEarningsCallDates(PathMetadata metadata) {
        super(ZacksEarningsCallDates.class, metadata);
    }

}

