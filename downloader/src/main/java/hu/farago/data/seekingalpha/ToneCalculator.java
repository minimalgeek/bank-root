package hu.farago.data.seekingalpha;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import hu.farago.repo.model.dao.mongo.HarvardWordsRepository;
import hu.farago.repo.model.dao.mongo.HenryWordsRepository;
import hu.farago.repo.model.entity.mongo.HarvardWords;
import hu.farago.repo.model.entity.mongo.HenryWords;
import hu.farago.repo.model.entity.mongo.embedded.HTone;
import hu.farago.repo.model.entity.mongo.embedded.Tone;
import hu.farago.repo.model.entity.mongo.embedded.ToneWithWords;

@Component
public class ToneCalculator {

	@Autowired
	private HarvardWordsRepository harvardWR;
	
	@Autowired
	private HenryWordsRepository henryWR;
	
	private Map<String, HarvardWords> harvardMap;
	private Map<String, HenryWords> henryMap;
	
	@PostConstruct
	private void buildDictionary() {
		harvardMap = Maps.newHashMap();
		henryMap = Maps.newHashMap();
		
		List<HarvardWords> harvardWords = harvardWR.findAll();
		
		for (HarvardWords harvardWord : harvardWords) {
			String word = harvardWord.getRealEntry();
			
			if (!harvardMap.containsKey(word)) {
				harvardMap.put(word, harvardWord);
				continue;
			}
			
			HarvardWords mapValue = harvardMap.get(word);
			
			if (harvardWord.isActive()) {
				mapValue.active = "X";
			}
			
			if (harvardWord.isNegative()) {
				mapValue.negativ = "X";
			}
			
			if (harvardWord.isOverstated()) {
				mapValue.overstated = "X";
			}
			
			if (harvardWord.isPassive()) {
				mapValue.passive = "X";
			}
			
			if (harvardWord.isPositive()) {
				mapValue.positiv = "X";
			}
			
			if (harvardWord.isStrong()) {
				mapValue.strong = "X";
			}
			
			if (harvardWord.isUnderstated()) {
				mapValue.understated = "X";
			}
			
			if (harvardWord.isWeak()) {
				mapValue.weak = "X";
			}
		}
		
		List<HenryWords> henryWords = henryWR.findAll();
		
		for (HenryWords henryWord : henryWords) {
			henryMap.put(henryWord.getRealWord(), henryWord);
		}
	}
	
	public Tone getToneOf(List<String> words) {
		Tone tone = new Tone();
		
		for (String word : words) {
			if (harvardMap.containsKey(StringUtils.lowerCase(word))) {
				HarvardWords harvardWord = harvardMap.get(word);
				
				tone.activeCount += (harvardWord.isActive() ? 1 : 0);
				tone.passiveCount += (harvardWord.isPassive() ? 1 : 0);
				
				tone.negativeCount += (harvardWord.isNegative() ? 1 : 0);
				tone.positiveCount += (harvardWord.isPositive() ? 1 : 0);
				
				tone.strongCount += (harvardWord.isStrong() ? 1 : 0);
				tone.weakCount += (harvardWord.isWeak() ? 1 : 0);
				
				tone.overstatedCount += (harvardWord.isOverstated() ? 1 : 0);
				tone.understatedCount += (harvardWord.isUnderstated() ? 1 : 0);
			}
		}
		
		return tone;
	}

	public HTone getHToneOf(List<String> words) {
		HTone tone = new HTone();
		
		for (String word : words) {
			if (henryMap.containsKey(StringUtils.lowerCase(word))) {
				HenryWords henryWord = henryMap.get(word);
				
				tone.negativeCount += (henryWord.isNegative() ? 1 : 0);
				tone.positiveCount += (henryWord.isPositive() ? 1 : 0);
			}
		}
		
		return tone;
	}
	
	public ToneWithWords getToneWithWords(List<String> words) {
		ToneWithWords tone = new ToneWithWords();
		
		tone.words = words.size();
		
		for (String word : words) {
			if (henryMap.containsKey(StringUtils.lowerCase(word))) {
				HenryWords henryWord = henryMap.get(word);
				
				tone.negativeWords += (henryWord.isNegative() ? 1 : 0);
				tone.positiveWords += (henryWord.isPositive() ? 1 : 0);
			}
		}
		
		return tone;
	}

}
