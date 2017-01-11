package hu.farago.repo.model.entity.mongo.embedded;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class HTone {
	
	public int positiveCount;
	public int negativeCount;
	
	// tone 1
	
	public double getHToneOnePN() {
		return (double)positiveCount / (double)negativeCount;
	}
	
	// tone 2
	
	public double getHToneTwoPN() {
		return (double)(positiveCount - negativeCount) / (double)(positiveCount + negativeCount);
	}
}
