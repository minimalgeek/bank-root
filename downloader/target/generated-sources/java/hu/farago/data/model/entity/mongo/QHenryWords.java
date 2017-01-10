package hu.farago.data.model.entity.mongo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHenryWords is a Querydsl query type for HenryWords
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHenryWords extends EntityPathBase<HenryWords> {

    private static final long serialVersionUID = -1223843160L;

    public static final QHenryWords henryWords = new QHenryWords("henryWords");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath negativ = createString("negativ");

    public final BooleanPath negative = createBoolean("negative");

    public final StringPath positiv = createString("positiv");

    public final BooleanPath positive = createBoolean("positive");

    public final StringPath realWord = createString("realWord");

    public final StringPath word = createString("word");

    public QHenryWords(String variable) {
        super(HenryWords.class, forVariable(variable));
    }

    public QHenryWords(Path<? extends HenryWords> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHenryWords(PathMetadata metadata) {
        super(HenryWords.class, metadata);
    }

}

