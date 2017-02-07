package hu.farago.repo.transformer;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.farago.repo.model.dao.mongo.AutomaticServiceErrorRepository;
import hu.farago.repo.model.dao.mongo.EarningsCallFlatRepository;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError.AutomaticService;
import hu.farago.repo.model.entity.mongo.EarningsCall;
import hu.farago.repo.model.entity.mongo.EarningsCallFlat;

/**
 * It's totally unnecessary, should be removed, as well as EarningsCallFlat class collection
 * @author neural
 *
 */
@Component
public class EarningsCallFlatSaver {

	private static final Logger LOGGER = LoggerFactory.getLogger(EarningsCallFlatSaver.class);

	@Autowired
	private EarningsCallFlatRepository repo;
	@Autowired
	private AutomaticServiceErrorRepository aser;

	public void saveEarningsCallFlat(EarningsCall call) {
		LOGGER.debug("saveEarningsCallFlat");
		if (call != null) {
			convertAndSave(call);
		}
	}

	public void saveEarningsCallsFlat(List<EarningsCall> calls) {
		LOGGER.debug("saveEarningsCallsFlat");
		if (calls != null) {
			for (EarningsCall call : calls) {
				convertAndSave(call);
			}
		}
	}

	private void convertAndSave(EarningsCall call) {
		try {
			repo.save(convert(call));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aser.save(new AutomaticServiceError(DateTime.now(), AutomaticService.SEEKING_ALPHA, e.getMessage()));
		}
	}

	private EarningsCallFlat convert(EarningsCall call) {
		EarningsCallFlat ecf = new EarningsCallFlat();
		ecf.id = call.id;
		ecf.tradingSymbol = call.tradingSymbol;

		ecf.hTone = call.hTone;
		ecf.qAndAHTone = call.qAndAHTone;

		ecf.dateNumber = call.dateNumber;
		ecf.timeNumber = call.timeNumber;

		ecf.q_and_a_wordSize = call.q_and_a_wordSize;
		ecf.wordSize = call.wordSize;
		return ecf;
	}

}
