package hu.farago.repo.model.entity.mongo;

import java.io.Serializable;
import java.math.BigInteger;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "zacks_earnings_call_dates_ii")
public class ZacksEarningsCallDates2 implements Serializable {

	private static final long serialVersionUID = -5487447385592478445L;

	@Id
	public BigInteger id;
	
	public DateTime nextReportDate;
	public String tradingSymbol;
	@Transient
	public String url;
	@Transient
	public boolean success;
	
	public DateTime nextReportDateInLocal() {
		return nextReportDate.withZone(DateTimeZone.getDefault());
	}
	
	public DateTime getNextReportDate() {
		return nextReportDate;
	}
	
	public String getUrl() {
		return url;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append(tradingSymbol)
			.append(nextReportDate)
			.toString();
	}
	
}
