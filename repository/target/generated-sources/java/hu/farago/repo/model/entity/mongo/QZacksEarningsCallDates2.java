package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QZacksEarningsCallDates2 is a Querydsl query type for ZacksEarningsCallDates2
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QZacksEarningsCallDates2 extends EntityPathBase<ZacksEarningsCallDates2> {

    private static final long serialVersionUID = 475089369L;

    public static final QZacksEarningsCallDates2 zacksEarningsCallDates2 = new QZacksEarningsCallDates2("zacksEarningsCallDates2");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<org.joda.time.DateTime> nextReportDate = createDateTime("nextReportDate", org.joda.time.DateTime.class);

    public final BooleanPath success = createBoolean("success");

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public final StringPath url = createString("url");

    public QZacksEarningsCallDates2(String variable) {
        super(ZacksEarningsCallDates2.class, forVariable(variable));
    }

    public QZacksEarningsCallDates2(Path<? extends ZacksEarningsCallDates2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZacksEarningsCallDates2(PathMetadata metadata) {
        super(ZacksEarningsCallDates2.class, metadata);
    }

}

