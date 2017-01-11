package hu.farago.repo.model.entity.mongo.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QToneWithWords is a Querydsl query type for ToneWithWords
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QToneWithWords extends EntityPathBase<ToneWithWords> {

    private static final long serialVersionUID = 1950489868L;

    public static final QToneWithWords toneWithWords = new QToneWithWords("toneWithWords");

    public final NumberPath<Integer> negativeWords = createNumber("negativeWords", Integer.class);

    public final NumberPath<Integer> positiveWords = createNumber("positiveWords", Integer.class);

    public final ListPath<String, StringPath> stemmedText = this.<String, StringPath>createList("stemmedText", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> words = createNumber("words", Integer.class);

    public QToneWithWords(String variable) {
        super(ToneWithWords.class, forVariable(variable));
    }

    public QToneWithWords(Path<? extends ToneWithWords> path) {
        super(path.getType(), path.getMetadata());
    }

    public QToneWithWords(PathMetadata metadata) {
        super(ToneWithWords.class, metadata);
    }

}

