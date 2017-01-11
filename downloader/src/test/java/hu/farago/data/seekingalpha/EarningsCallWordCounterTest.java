package hu.farago.data.seekingalpha;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import hu.farago.data.AbstractRootTest;
import hu.farago.repo.model.entity.mongo.EarningsCall;
import hu.farago.repo.model.entity.mongo.embedded.HTone;
import hu.farago.repo.model.entity.mongo.embedded.Tone;

public class EarningsCallWordCounterTest extends AbstractRootTest {

	private EarningsCall sampleCall;

	@Autowired
	private ToneCalculator toneCalculator;

	@Before
	public void beforeClass() {
		if (sampleCall == null) {
			sampleCall = new EarningsCall();
			sampleCall.words = Lists.newArrayList("win", "lose", "force", "strong", "weak", "drive", "terrible",
					"awful", "awsome");
		}
	}

	@Test
	public void collectToneForSampleArticle() {
		Tone tone = toneCalculator.getToneOf(sampleCall.words);
		Assert.assertNotNull(tone);

		Assert.assertNotEquals(tone.getToneOneAP(), 0);
		Assert.assertNotEquals(tone.getToneOneOU(), 0);
		Assert.assertNotEquals(tone.getToneOnePN(), 0);
		Assert.assertNotEquals(tone.getToneOneSW(), 0);

		Assert.assertNotEquals(tone.getToneTwoAP(), 0);
		Assert.assertNotEquals(tone.getToneTwoOU(), 0);
		Assert.assertNotEquals(tone.getToneTwoPN(), 0);
		Assert.assertNotEquals(tone.getToneTwoSW(), 0);
	}

	@Test
	public void collectHToneForSampleArticle() {
		HTone tone = toneCalculator.getHToneOf(sampleCall.words);
		Assert.assertNotNull(tone);

		Assert.assertNotEquals(tone.getHToneOnePN(), 0);
		Assert.assertNotEquals(tone.getHToneTwoPN(), 0);
	}

}
