package hu.farago.data.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInsiderData is a Querydsl query type for InsiderData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInsiderData extends EntityPathBase<InsiderData> {

    private static final long serialVersionUID = 1436697961L;

    public static final QInsiderData insiderData = new QInsiderData("insiderData");

    public final DateTimePath<org.joda.time.DateTime> acceptanceDate = createDateTime("acceptanceDate", org.joda.time.DateTime.class);

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath issuerName = createString("issuerName");

    public final StringPath issuerTradingSymbol = createString("issuerTradingSymbol");

    public final EnumPath<InsiderData.OwnerRelationShip> ownerRelationShip = createEnum("ownerRelationShip", InsiderData.OwnerRelationShip.class);

    public final NumberPath<Double> pricePerShare = createNumber("pricePerShare", Double.class);

    public final StringPath reportingOwnerName = createString("reportingOwnerName");

    public final NumberPath<Double> sharesOwned = createNumber("sharesOwned", Double.class);

    public final NumberPath<Double> totalValue = createNumber("totalValue", Double.class);

    public final DateTimePath<org.joda.time.DateTime> transactionDate = createDateTime("transactionDate", org.joda.time.DateTime.class);

    public final NumberPath<Double> transactionShares = createNumber("transactionShares", Double.class);

    public final EnumPath<InsiderData.BuySell> type = createEnum("type", InsiderData.BuySell.class);

    public QInsiderData(String variable) {
        super(InsiderData.class, forVariable(variable));
    }

    public QInsiderData(Path<? extends InsiderData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInsiderData(PathMetadata metadata) {
        super(InsiderData.class, metadata);
    }

}

