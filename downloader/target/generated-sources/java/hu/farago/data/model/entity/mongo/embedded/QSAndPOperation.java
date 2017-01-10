package hu.farago.data.model.entity.mongo.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSAndPOperation is a Querydsl query type for SAndPOperation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSAndPOperation extends EntityPathBase<SAndPOperation> {

    private static final long serialVersionUID = 1658756520L;

    public static final QSAndPOperation sAndPOperation = new QSAndPOperation("sAndPOperation");

    public final EnumPath<SAndPOperation.Event> event = createEnum("event", SAndPOperation.Event.class);

    public final DateTimePath<org.joda.time.DateTime> eventDate = createDateTime("eventDate", org.joda.time.DateTime.class);

    public final EnumPath<SAndPOperation.SAndPGroup> indexGroup = createEnum("indexGroup", SAndPOperation.SAndPGroup.class);

    public QSAndPOperation(String variable) {
        super(SAndPOperation.class, forVariable(variable));
    }

    public QSAndPOperation(Path<? extends SAndPOperation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSAndPOperation(PathMetadata metadata) {
        super(SAndPOperation.class, metadata);
    }

}

