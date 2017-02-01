package hu.farago.repo.model.entity.mongo;

import java.io.Serializable;
import java.math.BigInteger;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import hu.farago.repo.model.entity.mongo.embedded.HTone;

/**
 * The original flattener script is:
 * 
 * 	db.earnings_call.aggregate( [
	   { $project : { 
	       _id: 1, 
	       tradingSymbol : 1, 
	       publishDate : 1, 
	       wordSize: { $size: { "$ifNull": [ "$words", [] ] }}, 
	       "h_tone.positiveCount": 1, 
	       "h_tone.negativeCount": 1,
	       q_and_a_wordSize: { $size: { "$ifNull": [ "$qAndAWords", [] ] }}, 
	       "q_and_a_h_tone.positiveCount": 1, 
	       "q_and_a_h_tone.negativeCount": 1,
	       }
	   },
	   { $out: "earnings_call_flat" }
	]);
 * 
 * @author neural
 *
 */


@Document(collection = "earnings_call_flat")
public class EarningsCallFlat implements Serializable {
	
	private static final long serialVersionUID = -1538987715086324171L;

	@Id
	public BigInteger id;
	public String tradingSymbol;
	public DateTime publishDate;
	public int wordSize;
	@Field("h_tone")
	public HTone hTone;
	public int q_and_a_wordSize;
	@Field("q_and_a_h_tone")
	public HTone qAndAHTone;
	
}
