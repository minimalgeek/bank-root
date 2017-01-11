package hu.farago.data.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;

import com.google.common.collect.Sets;

import hu.farago.data.nasdaq.CompanyListDownloader;
import hu.farago.data.nasdaq.Exchange;
import hu.farago.data.nasdaq.HistoricalShortInterestLoader;
import hu.farago.data.nasdaq.IPODownloader;
import hu.farago.data.nasdaq.ShortInterestDownloader;
import hu.farago.repo.model.dao.mongo.IPOActivityRepository;
import hu.farago.repo.model.dao.mongo.ShortInterestRepository;
import hu.farago.repo.model.entity.mongo.IPOActivity;
import hu.farago.repo.model.entity.mongo.ShortInterest;

@Controller
public class NasdaqDownloadService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(NasdaqDownloadService.class);

	@Autowired
	private CompanyListDownloader companyDownloader;

	@Autowired
	private ShortInterestDownloader shortInterestDownloader;

	@Autowired
	private HistoricalShortInterestLoader historicalShortInterestLoader;

	@Autowired
	private ShortInterestRepository repository;
	
	@Autowired
	private IPODownloader ipoDownloader;
	
	@Autowired
	private IPOActivityRepository ipoRepository;

	//@RequestMapping(value = "/downloadShortInterestData", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object[] downloadShortInterestData() {
		LOGGER.info("downloadShortInterestData");
		Set<String> companies = Sets.newConcurrentHashSet();

		try {
			addCompanyListToCompanies(companies, Exchange.NASDAQ);
			addCompanyListToCompanies(companies, Exchange.NYSE);
			addCompanyListToCompanies(companies, Exchange.AMEX);

			for (String tradingSymbol : companies) {
				List<ShortInterest> interests = repository
						.findByTradingSymbol(tradingSymbol);
				List<ShortInterest> newInterests = shortInterestDownloader
						.downloadShortInterestsForTradingSymbol(tradingSymbol);

				newInterests.forEach(item -> {
					if (!interests.contains(item)) {
						repository.save(item);
					}
				});
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return companies.toArray();
	}

//	@RequestMapping(value = "/downloadHistoricalShortInterest", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void downloadHistoricalShortInterest() {
		LOGGER.info("downloadHistoricalShortInterest");
		try {
			historicalShortInterestLoader
					.importAllHistoricalDataFromDirectory();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
//	@RequestMapping(value = "/downloadAllIPOActivity", method = RequestMethod.GET, produces = { MediaType.TEXT_PLAIN_VALUE })
	public String downloadAllIPOActivity() {
		LOGGER.info("downloadAllIPOActivity");
		try {
			ipoRepository.deleteAll();
			List<IPOActivity> list = ipoDownloader.downloadAllActivity();
			ipoRepository.save(list);
			
			String msg = list.size() + " activities saved";
			LOGGER.info(msg);
			return msg;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	private boolean addCompanyListToCompanies(Set<String> companies, Exchange ex)
			throws IOException, SAXException, TikaException {
		return companies.addAll(Sets.newConcurrentHashSet(companyDownloader
				.downloadExchangeCompanyList(ex)));
	}
}