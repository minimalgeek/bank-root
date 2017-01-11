package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInsiderDataGroup is a Querydsl query type for InsiderDataGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInsiderDataGroup extends EntityPathBase<InsiderDataGroup> {

    private static final long serialVersionUID = -891952546L;

    public static final QInsiderDataGroup insiderDataGroup = new QInsiderDataGroup("insiderDataGroup");

    public final DateTimePath<org.joda.time.DateTime> acceptanceDate = createDateTime("acceptanceDate", org.joda.time.DateTime.class);

    public final ListPath<hu.farago.repo.model.entity.mongo.embedded.FormData, hu.farago.repo.model.entity.mongo.embedded.QFormData> formDataList = this.<hu.farago.repo.model.entity.mongo.embedded.FormData, hu.farago.repo.model.entity.mongo.embedded.QFormData>createList("formDataList", hu.farago.repo.model.entity.mongo.embedded.FormData.class, hu.farago.repo.model.entity.mongo.embedded.QFormData.class, PathInits.DIRECT2);

    public final StringPath formURL = createString("formURL");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath issuerName = createString("issuerName");

    public final StringPath issuerTradingSymbol = createString("issuerTradingSymbol");

    public final EnumPath<InsiderDataGroup.OwnerRelationShip> ownerRelationShip = createEnum("ownerRelationShip", InsiderDataGroup.OwnerRelationShip.class);

    public final StringPath reportingOwnerName = createString("reportingOwnerName");

    public final DateTimePath<org.joda.time.DateTime> transactionDate = createDateTime("transactionDate", org.joda.time.DateTime.class);

    public QInsiderDataGroup(String variable) {
        super(InsiderDataGroup.class, forVariable(variable));
    }

    public QInsiderDataGroup(Path<? extends InsiderDataGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInsiderDataGroup(PathMetadata metadata) {
        super(InsiderDataGroup.class, metadata);
    }

}

