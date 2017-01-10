package hu.farago.data.seekingalpha.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStockData is a Querydsl query type for StockData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStockData extends EntityPathBase<StockData> {

    private static final long serialVersionUID = -730614624L;

    public static final QStockData stockData = new QStockData("stockData");

    public final NumberPath<Double> closePrice = createNumber("closePrice", Double.class);

    public final DateTimePath<org.joda.time.DateTime> dateTime = createDateTime("dateTime", org.joda.time.DateTime.class);

    public QStockData(String variable) {
        super(StockData.class, forVariable(variable));
    }

    public QStockData(Path<? extends StockData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStockData(PathMetadata metadata) {
        super(StockData.class, metadata);
    }

}

