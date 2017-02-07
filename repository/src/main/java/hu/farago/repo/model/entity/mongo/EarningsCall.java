package hu.farago.repo.model.entity.mongo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import hu.farago.repo.model.entity.mongo.embedded.HTone;
import hu.farago.repo.model.entity.mongo.embedded.Tone;

@Document(collection = "earnings_call")
public class EarningsCall implements Serializable {
	
	private static final long serialVersionUID = -7825655570337352878L;
	
	@Id
	public BigInteger id;
	public String tradingSymbol;
	public String rawText;
	public DateTime zacksEarningsCallDate;
	public DateTime publishDate;
	public String url;
	public List<String> words;
	
	@Field("tone")
	public Tone tone;
	@Field("h_tone")
	public HTone hTone;
	
	public String qAndAText;
	public List<String> qAndAWords;
	
	@Field("q_and_a_tone")
	public Tone qAndATone;
	@Field("q_and_a_h_tone")
	public HTone qAndAHTone;
	
	@Field("date_number")
	public long dateNumber;
	@Field("time_number")
	public long timeNumber;
	
	public int wordSize;
	public int q_and_a_wordSize;
	
	public double sumOfSharesOwnedBeforePublishDate;
	public double sumOfBuyTransactionShares;
	public double sumOfSellTransactionShares;
}
