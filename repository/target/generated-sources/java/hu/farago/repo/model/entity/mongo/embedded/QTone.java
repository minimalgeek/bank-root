package hu.farago.repo.model.entity.mongo.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTone is a Querydsl query type for Tone
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTone extends EntityPathBase<Tone> {

    private static final long serialVersionUID = -1927014857L;

    public static final QTone tone = new QTone("tone");

    public final NumberPath<Integer> activeCount = createNumber("activeCount", Integer.class);

    public final NumberPath<Integer> negativeCount = createNumber("negativeCount", Integer.class);

    public final NumberPath<Integer> overstatedCount = createNumber("overstatedCount", Integer.class);

    public final NumberPath<Integer> passiveCount = createNumber("passiveCount", Integer.class);

    public final NumberPath<Integer> positiveCount = createNumber("positiveCount", Integer.class);

    public final NumberPath<Integer> strongCount = createNumber("strongCount", Integer.class);

    public final NumberPath<Double> toneOneAP = createNumber("toneOneAP", Double.class);

    public final NumberPath<Double> toneOneOU = createNumber("toneOneOU", Double.class);

    public final NumberPath<Double> toneOnePN = createNumber("toneOnePN", Double.class);

    public final NumberPath<Double> toneOneSW = createNumber("toneOneSW", Double.class);

    public final NumberPath<Double> toneTwoAP = createNumber("toneTwoAP", Double.class);

    public final NumberPath<Double> toneTwoOU = createNumber("toneTwoOU", Double.class);

    public final NumberPath<Double> toneTwoPN = createNumber("toneTwoPN", Double.class);

    public final NumberPath<Double> toneTwoSW = createNumber("toneTwoSW", Double.class);

    public final NumberPath<Integer> understatedCount = createNumber("understatedCount", Integer.class);

    public final NumberPath<Integer> weakCount = createNumber("weakCount", Integer.class);

    public QTone(String variable) {
        super(Tone.class, forVariable(variable));
    }

    public QTone(Path<? extends Tone> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTone(PathMetadata metadata) {
        super(Tone.class, metadata);
    }

}

