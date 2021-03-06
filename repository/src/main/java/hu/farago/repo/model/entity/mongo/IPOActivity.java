package hu.farago.repo.model.entity.mongo;

import java.math.BigInteger;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ipo_activity")
public class IPOActivity {

	@Id
	public BigInteger id;
	public String name;
	public String tradingSymbol;
	public String market;
	public double price;
	public DateTime datePriced;
	public double shares;
	public double offerAmount;
	
	public String getName() {
		return name;
	}
	
	public String getTradingSymbol() {
		return tradingSymbol;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getShares() {
		return shares;
	}
	
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		
		builder.append(name);
		builder.append(tradingSymbol);
		builder.append(market);
		builder.append(price);
		builder.append(datePriced);
		builder.append(shares);
		builder.append(offerAmount);
		
		return builder.toString();
	}
	
}
