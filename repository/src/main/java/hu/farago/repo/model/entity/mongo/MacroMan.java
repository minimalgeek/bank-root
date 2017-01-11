package hu.farago.repo.model.entity.mongo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;

import hu.farago.repo.model.entity.mongo.embedded.Contributor;
import hu.farago.repo.model.entity.mongo.embedded.ToneWithWords;

@Document(collection = "macro_man")
public class MacroMan extends UrlBasedEntity implements Serializable {

	private static final long serialVersionUID = 5530376340387092036L;

	public MacroMan() {
		contributors = Lists.newArrayList();
	}
	
	@Id
	public BigInteger id;
	public List<Contributor> contributors;
	
	public ToneWithWords tone;
}
