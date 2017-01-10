package hu.farago.data.edgar.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEdgarXML is a Querydsl query type for EdgarXML
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QEdgarXML extends BeanPath<EdgarXML> {

    private static final long serialVersionUID = 1887221547L;

    public static final QEdgarXML edgarXML = new QEdgarXML("edgarXML");

    public final ListPath<DerivativeTransaction, SimplePath<DerivativeTransaction>> derivativeTable = this.<DerivativeTransaction, SimplePath<DerivativeTransaction>>createList("derivativeTable", DerivativeTransaction.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> documentType = createNumber("documentType", Integer.class);

    public final SimplePath<Issuer> issuer = createSimple("issuer", Issuer.class);

    public final ListPath<NonDerivativeTransaction, SimplePath<NonDerivativeTransaction>> nonDerivativeTable = this.<NonDerivativeTransaction, SimplePath<NonDerivativeTransaction>>createList("nonDerivativeTable", NonDerivativeTransaction.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> notSubjectToSection16 = createNumber("notSubjectToSection16", Integer.class);

    public final SimplePath<OwnerSignature> ownerSignature = createSimple("ownerSignature", OwnerSignature.class);

    public final DateTimePath<org.joda.time.DateTime> periodOfReport = createDateTime("periodOfReport", org.joda.time.DateTime.class);

    public final SimplePath<ReportingOwner> reportingOwner = createSimple("reportingOwner", ReportingOwner.class);

    public final StringPath schemaVersion = createString("schemaVersion");

    public QEdgarXML(String variable) {
        super(EdgarXML.class, forVariable(variable));
    }

    public QEdgarXML(Path<? extends EdgarXML> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEdgarXML(PathMetadata metadata) {
        super(EdgarXML.class, metadata);
    }

}

