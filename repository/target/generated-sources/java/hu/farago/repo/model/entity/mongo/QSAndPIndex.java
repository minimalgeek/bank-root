package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSAndPIndex is a Querydsl query type for SAndPIndex
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSAndPIndex extends EntityPathBase<SAndPIndex> {

    private static final long serialVersionUID = -2074632251L;

    public static final QSAndPIndex sAndPIndex = new QSAndPIndex("sAndPIndex");

    public final StringPath company = createString("company");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final ListPath<hu.farago.repo.model.entity.mongo.embedded.SAndPOperation, hu.farago.repo.model.entity.mongo.embedded.QSAndPOperation> operations = this.<hu.farago.repo.model.entity.mongo.embedded.SAndPOperation, hu.farago.repo.model.entity.mongo.embedded.QSAndPOperation>createList("operations", hu.farago.repo.model.entity.mongo.embedded.SAndPOperation.class, hu.farago.repo.model.entity.mongo.embedded.QSAndPOperation.class, PathInits.DIRECT2);

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public QSAndPIndex(String variable) {
        super(SAndPIndex.class, forVariable(variable));
    }

    public QSAndPIndex(Path<? extends SAndPIndex> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSAndPIndex(PathMetadata metadata) {
        super(SAndPIndex.class, metadata);
    }

}

