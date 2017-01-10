package hu.farago.data.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEdgarData is a Querydsl query type for EdgarData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEdgarData extends EntityPathBase<EdgarData> {

    private static final long serialVersionUID = -572339188L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEdgarData edgarData = new QEdgarData("edgarData");

    public final hu.farago.data.edgar.dto.QEdgarXML edgarXML;

    public final StringPath formURL = createString("formURL");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath tradingSymbol = createString("tradingSymbol");

    public QEdgarData(String variable) {
        this(EdgarData.class, forVariable(variable), INITS);
    }

    public QEdgarData(Path<? extends EdgarData> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEdgarData(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEdgarData(PathMetadata metadata, PathInits inits) {
        this(EdgarData.class, metadata, inits);
    }

    public QEdgarData(Class<? extends EdgarData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.edgarXML = inits.isInitialized("edgarXML") ? new hu.farago.data.edgar.dto.QEdgarXML(forProperty("edgarXML")) : null;
    }

}

