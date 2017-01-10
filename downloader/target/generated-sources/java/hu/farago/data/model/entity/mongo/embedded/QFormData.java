package hu.farago.data.model.entity.mongo.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFormData is a Querydsl query type for FormData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFormData extends EntityPathBase<FormData> {

    private static final long serialVersionUID = -1316397157L;

    public static final QFormData formData = new QFormData("formData");

    public final StringPath acquired = createString("acquired");

    public final NumberPath<Double> amountOfAcquired = createNumber("amountOfAcquired", Double.class);

    public final NumberPath<Integer> code01 = createNumber("code01", Integer.class);

    public final StringPath code02 = createString("code02");

    public final NumberPath<Integer> code03 = createNumber("code03", Integer.class);

    public final StringPath code04 = createString("code04");

    public final StringPath ownershipForm = createString("ownershipForm");

    public final NumberPath<Double> priceOfAcquired = createNumber("priceOfAcquired", Double.class);

    public final NumberPath<Double> sharesOwned = createNumber("sharesOwned", Double.class);

    public final StringPath titleOfSecurity = createString("titleOfSecurity");

    public final DateTimePath<org.joda.time.DateTime> transactionDate = createDateTime("transactionDate", org.joda.time.DateTime.class);

    public QFormData(String variable) {
        super(FormData.class, forVariable(variable));
    }

    public QFormData(Path<? extends FormData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFormData(PathMetadata metadata) {
        super(FormData.class, metadata);
    }

}

