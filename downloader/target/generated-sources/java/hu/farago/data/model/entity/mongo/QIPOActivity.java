package hu.farago.data.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIPOActivity is a Querydsl query type for IPOActivity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIPOActivity extends EntityPathBase<IPOActivity> {

    private static final long serialVersionUID = -2071797760L;

    public static final QIPOActivity iPOActivity = new QIPOActivity("iPOActivity");

    public final DateTimePath<org.joda.time.DateTime> datePriced = createDateTime("datePriced", org.joda.time.DateTime.class);

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath market = createString("market");

    public final StringPath name = createString("name");

    public final NumberPath<Double> offerAmount = createNumber("offerAmount", Double.class);

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Double> shares = createNumber("shares", Double.class);

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public QIPOActivity(String variable) {
        super(IPOActivity.class, forVariable(variable));
    }

    public QIPOActivity(Path<? extends IPOActivity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIPOActivity(PathMetadata metadata) {
        super(IPOActivity.class, metadata);
    }

}

