package hu.farago.data.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEdgar10QData is a Querydsl query type for Edgar10QData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEdgar10QData extends EntityPathBase<Edgar10QData> {

    private static final long serialVersionUID = -655204006L;

    public static final QEdgar10QData edgar10QData = new QEdgar10QData("edgar10QData");

    public final NumberPath<Long> footnoteLength = createNumber("footnoteLength", Long.class);

    public final NumberPath<Long> formLength = createNumber("formLength", Long.class);

    public final StringPath formURL = createString("formURL");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<org.joda.time.DateTime> reportDate = createDateTime("reportDate", org.joda.time.DateTime.class);

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public QEdgar10QData(String variable) {
        super(Edgar10QData.class, forVariable(variable));
    }

    public QEdgar10QData(Path<? extends Edgar10QData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEdgar10QData(PathMetadata metadata) {
        super(Edgar10QData.class, metadata);
    }

}

