package hu.farago.repo.model.entity.mongo.embedded;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contributor {
	
	public String name;
	public ToneWithWords tone;
	
}
