package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMacroMan is a Querydsl query type for MacroMan
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMacroMan extends EntityPathBase<MacroMan> {

    private static final long serialVersionUID = 1148430765L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMacroMan macroMan = new QMacroMan("macroMan");

    public final QUrlBasedEntity _super = new QUrlBasedEntity(this);

    public final ListPath<hu.farago.repo.model.entity.mongo.embedded.Contributor, hu.farago.repo.model.entity.mongo.embedded.QContributor> contributors = this.<hu.farago.repo.model.entity.mongo.embedded.Contributor, hu.farago.repo.model.entity.mongo.embedded.QContributor>createList("contributors", hu.farago.repo.model.entity.mongo.embedded.Contributor.class, hu.farago.repo.model.entity.mongo.embedded.QContributor.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    //inherited
    public final DateTimePath<org.joda.time.DateTime> publicationDate = _super.publicationDate;

    public final hu.farago.repo.model.entity.mongo.embedded.QToneWithWords tone;

    //inherited
    public final StringPath url = _super.url;

    public QMacroMan(String variable) {
        this(MacroMan.class, forVariable(variable), INITS);
    }

    public QMacroMan(Path<? extends MacroMan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMacroMan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMacroMan(PathMetadata metadata, PathInits inits) {
        this(MacroMan.class, metadata, inits);
    }

    public QMacroMan(Class<? extends MacroMan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tone = inits.isInitialized("tone") ? new hu.farago.repo.model.entity.mongo.embedded.QToneWithWords(forProperty("tone")) : null;
    }

}

