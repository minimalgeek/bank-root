package hu.farago.mongo.exporter.dto;

import org.joda.time.DateTime;

import hu.farago.repo.model.entity.mongo.EarningsCall;

public class EarningsCallDTO {

	public DateTime publishDate;
	
	public double hToneOnePN;
	public double hToneTwoPN;
	
	public double toneOnePN;
	public double toneOneAP;
	public double toneOneSW;
	public double toneOneOU;
	
	public double toneTwoPN;
	public double toneTwoAP;
	public double toneTwoSW;
	public double toneTwoOU;
	
	public EarningsCallDTO(EarningsCall call) {
		this.publishDate = call.publishDate;
		
		this.hToneOnePN = call.hTone.getHToneOnePN();
		this.hToneTwoPN = call.hTone.getHToneTwoPN();
		
		this.toneOnePN = call.tone.getToneOnePN();
		this.toneOneAP = call.tone.getToneOneAP();
		this.toneOneSW = call.tone.getToneOneSW();
		this.toneOneOU = call.tone.getToneOneOU();
		
		this.toneTwoPN = call.tone.getToneTwoPN();
		this.toneTwoAP = call.tone.getToneTwoAP();
		this.toneTwoSW = call.tone.getToneTwoSW();
		this.toneTwoOU = call.tone.getToneTwoOU();
	}
	
}
