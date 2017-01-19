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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import hu.farago.data.nasdaq.CompanyListDownloader;
import hu.farago.data.nasdaq.Exchange;
import hu.farago.data.nasdaq.HistoricalShortInterestLoader;
import hu.farago.data.nasdaq.IPODownloader;
import hu.farago.data.nasdaq.ShortInterestDownloader;
import hu.farago.data.utils.AutomaticServiceErrorUtils;
import hu.farago.repo.model.dao.mongo.IPOActivityRepository;
import hu.farago.repo.model.dao.mongo.ShortInterestRepository;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError.AutomaticService;
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
	
	@Autowired
	private AutomaticServiceErrorUtils aseu;

	//@RequestMapping(value = "/downloadShortInterestData", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ShortInterest> downloadShortInterestData() {
		LOGGER.info("downloadShortInterestData");
		List<ShortInterest> ret = Lists.newArrayList();
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
						ret.add(item);
					}
				});
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.NASDAQ, e.getMessage());
		}

		return ret;
	}

//	@RequestMapping(value = "/downloadHistoricalShortInterest", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ShortInterest> downloadHistoricalShortInterest() {
		LOGGER.info("downloadHistoricalShortInterest");
		try {
			return historicalShortInterestLoader
					.importAllHistoricalDataFromDirectory();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.NASDAQ, e.getMessage());
		}
		return null;
	}
	
//	@RequestMapping(value = "/downloadAllIPOActivity", method = RequestMethod.GET, produces = { MediaType.TEXT_PLAIN_VALUE })
	public List<IPOActivity> downloadAllIPOActivity() {
		LOGGER.info("downloadAllIPOActivity");
		try {
			ipoRepository.deleteAll();
			List<IPOActivity> list = ipoDownloader.downloadAllActivity();
			ipoRepository.save(list);
			
			return list;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			aseu.saveError(AutomaticService.NASDAQ, e.getMessage());
		}
		return null;
	}
	
	private boolean addCompanyListToCompanies(Set<String> companies, Exchange ex)
			throws IOException, SAXException, TikaException {
		return companies.addAll(Sets.newConcurrentHashSet(companyDownloader
				.downloadExchangeCompanyList(ex)));
	}
}
