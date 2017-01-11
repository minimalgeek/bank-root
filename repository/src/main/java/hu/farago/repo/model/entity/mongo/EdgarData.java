package hu.farago.repo.model.entity.mongo;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import hu.farago.repo.model.entity.mongo.embedded.edgar.EdgarXML;

@Document(collection = "edgar_data")
public class EdgarData {
	
	@Id
	public BigInteger id;
	public String formURL;
	public String tradingSymbol;
	
	public EdgarXML edgarXML;
	
}
