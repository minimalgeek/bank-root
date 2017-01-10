package hu.farago.data.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAutomaticServiceError is a Querydsl query type for AutomaticServiceError
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAutomaticServiceError extends EntityPathBase<AutomaticServiceError> {

    private static final long serialVersionUID = -16760953L;

    public static final QAutomaticServiceError automaticServiceError = new QAutomaticServiceError("automaticServiceError");

    public final EnumPath<AutomaticServiceError.AutomaticService> automaticService = createEnum("automaticService", AutomaticServiceError.AutomaticService.class);

    public final DateTimePath<org.joda.time.DateTime> dateTime = createDateTime("dateTime", org.joda.time.DateTime.class);

    public final StringPath dateTimeString = createString("dateTimeString");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath message = createString("message");

    public QAutomaticServiceError(String variable) {
        super(AutomaticServiceError.class, forVariable(variable));
    }

    public QAutomaticServiceError(Path<? extends AutomaticServiceError> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAutomaticServiceError(PathMetadata metadata) {
        super(AutomaticServiceError.class, metadata);
    }

}

