package hu.farago.repo.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHarvardWords is a Querydsl query type for HarvardWords
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHarvardWords extends EntityPathBase<HarvardWords> {

    private static final long serialVersionUID = -1612127150L;

    public static final QHarvardWords harvardWords = new QHarvardWords("harvardWords");

    public final BooleanPath active = createBoolean("active");

    public final StringPath entry = createString("entry");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath negativ = createString("negativ");

    public final BooleanPath negative = createBoolean("negative");

    public final BooleanPath overstated = createBoolean("overstated");

    public final BooleanPath passive = createBoolean("passive");

    public final StringPath positiv = createString("positiv");

    public final BooleanPath positive = createBoolean("positive");

    public final StringPath realEntry = createString("realEntry");

    public final BooleanPath strong = createBoolean("strong");

    public final BooleanPath understated = createBoolean("understated");

    public final BooleanPath weak = createBoolean("weak");

    public QHarvardWords(String variable) {
        super(HarvardWords.class, forVariable(variable));
    }

    public QHarvardWords(Path<? extends HarvardWords> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHarvardWords(PathMetadata metadata) {
        super(HarvardWords.class, metadata);
    }

}

