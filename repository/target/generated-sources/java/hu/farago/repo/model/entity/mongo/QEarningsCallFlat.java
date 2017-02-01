package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEarningsCallFlat is a Querydsl query type for EarningsCallFlat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEarningsCallFlat extends EntityPathBase<EarningsCallFlat> {

    private static final long serialVersionUID = -1840815233L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEarningsCallFlat earningsCallFlat = new QEarningsCallFlat("earningsCallFlat");

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone hTone;

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<org.joda.time.DateTime> publishDate = createDateTime("publishDate", org.joda.time.DateTime.class);

    public final NumberPath<Integer> q_and_a_wordSize = createNumber("q_and_a_wordSize", Integer.class);

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone qAndAHTone;

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public final NumberPath<Integer> wordSize = createNumber("wordSize", Integer.class);

    public QEarningsCallFlat(String variable) {
        this(EarningsCallFlat.class, forVariable(variable), INITS);
    }

    public QEarningsCallFlat(Path<? extends EarningsCallFlat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEarningsCallFlat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEarningsCallFlat(PathMetadata metadata, PathInits inits) {
        this(EarningsCallFlat.class, metadata, inits);
    }

    public QEarningsCallFlat(Class<? extends EarningsCallFlat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hTone = inits.isInitialized("hTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("hTone")) : null;
        this.qAndAHTone = inits.isInitialized("qAndAHTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("qAndAHTone")) : null;
    }

}

