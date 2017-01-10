package hu.farago.data.seekingalpha.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHTone is a Querydsl query type for HTone
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHTone extends EntityPathBase<HTone> {

    private static final long serialVersionUID = -1636852038L;

    public static final QHTone hTone = new QHTone("hTone");

    public final NumberPath<Double> HToneOnePN = createNumber("HToneOnePN", Double.class);

    public final NumberPath<Double> HToneTwoPN = createNumber("HToneTwoPN", Double.class);

    public final NumberPath<Integer> negativeCount = createNumber("negativeCount", Integer.class);

    public final NumberPath<Integer> positiveCount = createNumber("positiveCount", Integer.class);

    public QHTone(String variable) {
        super(HTone.class, forVariable(variable));
    }

    public QHTone(Path<? extends HTone> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHTone(PathMetadata metadata) {
        super(HTone.class, metadata);
    }

}

