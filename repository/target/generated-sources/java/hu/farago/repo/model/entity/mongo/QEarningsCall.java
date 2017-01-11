package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEarningsCall is a Querydsl query type for EarningsCall
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEarningsCall extends EntityPathBase<EarningsCall> {

    private static final long serialVersionUID = 1488311014L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEarningsCall earningsCall = new QEarningsCall("earningsCall");

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone hTone;

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<org.joda.time.DateTime> publishDate = createDateTime("publishDate", org.joda.time.DateTime.class);

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone qAndAHTone;

    public final StringPath qAndAText = createString("qAndAText");

    public final hu.farago.repo.model.entity.mongo.embedded.QTone qAndATone;

    public final ListPath<String, StringPath> qAndAWords = this.<String, StringPath>createList("qAndAWords", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath rawText = createString("rawText");

    public final ListPath<hu.farago.repo.model.entity.mongo.embedded.StockData, hu.farago.repo.model.entity.mongo.embedded.QStockData> stockData = this.<hu.farago.repo.model.entity.mongo.embedded.StockData, hu.farago.repo.model.entity.mongo.embedded.QStockData>createList("stockData", hu.farago.repo.model.entity.mongo.embedded.StockData.class, hu.farago.repo.model.entity.mongo.embedded.QStockData.class, PathInits.DIRECT2);

    public final NumberPath<Double> sumOfBuyTransactionShares = createNumber("sumOfBuyTransactionShares", Double.class);

    public final NumberPath<Double> sumOfSellTransactionShares = createNumber("sumOfSellTransactionShares", Double.class);

    public final NumberPath<Double> sumOfSharesOwnedBeforePublishDate = createNumber("sumOfSharesOwnedBeforePublishDate", Double.class);

    public final hu.farago.repo.model.entity.mongo.embedded.QTone tone;

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public final StringPath url = createString("url");

    public final ListPath<String, StringPath> words = this.<String, StringPath>createList("words", String.class, StringPath.class, PathInits.DIRECT2);

    public QEarningsCall(String variable) {
        this(EarningsCall.class, forVariable(variable), INITS);
    }

    public QEarningsCall(Path<? extends EarningsCall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEarningsCall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEarningsCall(PathMetadata metadata, PathInits inits) {
        this(EarningsCall.class, metadata, inits);
    }

    public QEarningsCall(Class<? extends EarningsCall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hTone = inits.isInitialized("hTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("hTone")) : null;
        this.qAndAHTone = inits.isInitialized("qAndAHTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("qAndAHTone")) : null;
        this.qAndATone = inits.isInitialized("qAndATone") ? new hu.farago.repo.model.entity.mongo.embedded.QTone(forProperty("qAndATone")) : null;
        this.tone = inits.isInitialized("tone") ? new hu.farago.repo.model.entity.mongo.embedded.QTone(forProperty("tone")) : null;
    }

}

