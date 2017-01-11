package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUrlBasedEntity is a Querydsl query type for UrlBasedEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUrlBasedEntity extends BeanPath<UrlBasedEntity> {

    private static final long serialVersionUID = -1886160634L;

    public static final QUrlBasedEntity urlBasedEntity = new QUrlBasedEntity("urlBasedEntity");

    public final DateTimePath<org.joda.time.DateTime> publicationDate = createDateTime("publicationDate", org.joda.time.DateTime.class);

    public final StringPath url = createString("url");

    public QUrlBasedEntity(String variable) {
        super(UrlBasedEntity.class, forVariable(variable));
    }

    public QUrlBasedEntity(Path<? extends UrlBasedEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUrlBasedEntity(PathMetadata metadata) {
        super(UrlBasedEntity.class, metadata);
    }

}

