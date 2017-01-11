package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOilReport is a Querydsl query type for OilReport
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOilReport extends EntityPathBase<OilReport> {

    private static final long serialVersionUID = 1452541383L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOilReport oilReport = new QOilReport("oilReport");

    public final QUrlBasedEntity _super = new QUrlBasedEntity(this);

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone demandTone;

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone highlightsTone;

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone oecdStocksTone;

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone overviewTone;

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone pricesTone;

    //inherited
    public final DateTimePath<org.joda.time.DateTime> publicationDate = _super.publicationDate;

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone refiningTone;

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone sumTotalTone;

    public final hu.farago.repo.model.entity.mongo.embedded.QHTone supplyTone;

    //inherited
    public final StringPath url = _super.url;

    public QOilReport(String variable) {
        this(OilReport.class, forVariable(variable), INITS);
    }

    public QOilReport(Path<? extends OilReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOilReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOilReport(PathMetadata metadata, PathInits inits) {
        this(OilReport.class, metadata, inits);
    }

    public QOilReport(Class<? extends OilReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.demandTone = inits.isInitialized("demandTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("demandTone")) : null;
        this.highlightsTone = inits.isInitialized("highlightsTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("highlightsTone")) : null;
        this.oecdStocksTone = inits.isInitialized("oecdStocksTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("oecdStocksTone")) : null;
        this.overviewTone = inits.isInitialized("overviewTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("overviewTone")) : null;
        this.pricesTone = inits.isInitialized("pricesTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("pricesTone")) : null;
        this.refiningTone = inits.isInitialized("refiningTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("refiningTone")) : null;
        this.sumTotalTone = inits.isInitialized("sumTotalTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("sumTotalTone")) : null;
        this.supplyTone = inits.isInitialized("supplyTone") ? new hu.farago.repo.model.entity.mongo.embedded.QHTone(forProperty("supplyTone")) : null;
    }

}

