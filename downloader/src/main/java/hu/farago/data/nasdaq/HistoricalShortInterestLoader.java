package hu.farago.data.nasdaq;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import hu.farago.repo.model.dao.mongo.ShortInterestRepository;
import hu.farago.repo.model.entity.mongo.ShortInterest;
import hu.farago.repo.utils.DateTimeUtils;

@Component
public class HistoricalShortInterestLoader {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HistoricalShortInterestLoader.class);

	@Value("${nasdaq.shortInterest.historicalPath}")
	private String historicalPath;

	@Autowired
	private ShortInterestRepository repository;

	public List<ShortInterest> importAllHistoricalDataFromDirectory() throws IOException {

		List<ShortInterest> ret = Lists.newLinkedList();
		Collection<File> files = FileUtils.listFiles(new File(historicalPath),
				new String[] { "csv" }, true);
		for (File file : files) {
			List<String> lines = FileUtils.readLines(file, Charset.forName("UTF-8"));
			if (lines.size() > 3) {
				for (String line : lines) {
					String[] parts = StringUtils.split(line, ',');
					try {
						ShortInterest interest = new ShortInterest();
						interest.settlementDate = DateTimeUtils
								.parseToMMDDYYYY_UTC(parts[0]);
						interest.shortInterest = NumberUtils.toDouble(parts[1]);
						interest.tradingSymbol = FilenameUtils.getBaseName(file.getName());
						
						repository.save(interest);
						ret.add(interest);
					} catch (Exception e) {
						LOGGER.error("Not parseable: " + line);
					}
				}
			}
		}
		return ret;
	}

}
