package hu.farago.repo.model.entity.mongo.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContributor is a Querydsl query type for Contributor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContributor extends EntityPathBase<Contributor> {

    private static final long serialVersionUID = 1206434934L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContributor contributor = new QContributor("contributor");

    public final StringPath name = createString("name");

    public final QToneWithWords tone;

    public QContributor(String variable) {
        this(Contributor.class, forVariable(variable), INITS);
    }

    public QContributor(Path<? extends Contributor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContributor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContributor(PathMetadata metadata, PathInits inits) {
        this(Contributor.class, metadata, inits);
    }

    public QContributor(Class<? extends Contributor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tone = inits.isInitialized("tone") ? new QToneWithWords(forProperty("tone")) : null;
    }

}

