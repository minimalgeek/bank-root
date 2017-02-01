package hu.farago.repo.transformer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import hu.farago.repo.model.dao.mongo.EarningsCallFlatRepository;
import hu.farago.repo.model.dao.mongo.EarningsCallRepository;
import hu.farago.repo.model.entity.mongo.EarningsCall;
import hu.farago.repo.model.entity.mongo.EarningsCallFlat;
import hu.farago.repo.model.entity.mongo.embedded.HTone;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EarningsCallFlatSaverTest {

	@Autowired
	private EarningsCallRepository ecRepo;
	@Autowired
	private EarningsCallFlatRepository ecFlatRepo;

	private EarningsCall ec1;
	private EarningsCall ec2;
	private EarningsCall ec3;

	@Before
	public void before() {
		ecRepo.deleteAll();
		ecFlatRepo.deleteAll();

		ec1 = new EarningsCall();
		ec1.id = BigInteger.valueOf(1);
		ec1.tradingSymbol = "AAPL";
		ec1.rawText = "Rutyoka";
		ec1.publishDate = DateTime.now();
		ec1.url = "hello://bello";
		ec1.words = Lists.newArrayList("a", "b", "c");
		ec1.hTone = new HTone();
		ec1.qAndAWords = Lists.newArrayList("d", "e", "f", "g");
		ec1.qAndAHTone = new HTone();

		ec2 = new EarningsCall();
		ec2.id = BigInteger.valueOf(2);
		ec2.tradingSymbol = "IBM";
		ec2.rawText = "Rutyoka";
		ec2.publishDate = DateTime.now().minusDays(1);
		ec2.url = "hello://bello";
		ec2.words = Lists.newArrayList("a", "b", "c");
		ec2.hTone = new HTone();
		ec2.qAndAWords = Lists.newArrayList("d", "e", "f", "g");
		ec2.qAndAHTone = new HTone();

		ec3 = new EarningsCall();
		ec3.id = BigInteger.valueOf(3);
		ec3.tradingSymbol = "NFLX";
		ec3.rawText = "Rutyoka";
		ec3.publishDate = DateTime.now().minusDays(2);
		ec3.url = "hello://bello";
		ec3.words = Lists.newArrayList("a", "b", "c");
		ec3.hTone = new HTone();
		ec3.qAndAWords = Lists.newArrayList("d", "e", "f", "g");
		ec3.qAndAHTone = new HTone();
	}

	@Test
	public void beforeEarningsCallSaveTest() {
		ec1 = ecRepo.saveFlat(ec1);
		List<EarningsCallFlat> flats = ecFlatRepo.findAll();
		assertEquals(1, flats.size());
		assertThat(flats.get(0).tradingSymbol).isEqualTo("AAPL");
	}

	@Test
	public void beforeEarningsCallsSaveTest() {
		List<EarningsCall> calls = Lists.newArrayList(ec2, ec3);
		ecRepo.saveFlat(calls);
		List<EarningsCallFlat> flats = ecFlatRepo.findAll();
		assertEquals(2, flats.size());
	}

}
