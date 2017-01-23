package hu.farago.vaadmin.tab.datadownloader;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.GridLayout;

import hu.farago.data.service.NasdaqDownloadService;
import hu.farago.repo.model.entity.mongo.IPOActivity;
import hu.farago.repo.model.entity.mongo.ShortInterest;
import hu.farago.vaadmin.tab.block.TabPartGrid;

@SpringComponent
@UIScope
public class ShortInterestDownloaderTab extends GridLayout {

	private static final long serialVersionUID = -4143512087173627823L;

	@Autowired
	private NasdaqDownloadService nasdaqDownloadService;

	public ShortInterestDownloaderTab() {
		super(3, 1);
		setSizeFull();
		setMargin(true);
		setSpacing(true);

//		nasdaq.shortInterest.urlBase = http://www.nasdaq.com/symbol/
//			nasdaq.shortInterest.urlEnd = /short-interest
		
		addComponent(new TabPartGrid<ShortInterest>(
				"Collect http://nasdaq.com/ short interest data",
				() -> nasdaqDownloadService.downloadShortInterestData(),
				"<p>Downloads the short interests based on the most fresh ticker lists found on "
				+ "<a href=\"http://www.nasdaq.com/screening/companies-by-name.aspx?letter=0&exchange=NASDAQ&render=download\">NASDAQ</a>, "
				+ "<a href=\"http://www.nasdaq.com/screening/companies-by-name.aspx?letter=0&exchange=NYSE&render=download\">NYSE</a>, "
				+ "<a href=\"http://www.nasdaq.com/screening/companies-by-name.aspx?letter=0&exchange=AMEX&render=download\">AMEX</a></p>"
				+ "<p>Visits all the ticker's url (e.g.: "
				+ "<a href=\"http://www.nasdaq.com/symbol/AAPL/short-interest\">http://www.nasdaq.com/symbol/AAPL/short-interest</a>) "
				+ "and collects all the short interest from there</p>"
				+ "<p>The data will be saved to <b>short_interest</b> mongo collection</p>",
				ShortInterest.class), 
			0, 0);
		
		addComponent(new TabPartGrid<ShortInterest>(
				"Collect historical short interest data",
				() -> nasdaqDownloadService.downloadHistoricalShortInterest(),
				"<p>Imports the short interests from the csv files under <b>z:/Quant_CO2/IMPORT/SHORT_INTEREST/</b> directory</p>"
				+ "<p>The algorithm is recursive, so the multilevel folder structure is supported</p>"
				+ "<p>The data will be saved to <b>short_interest</b> mongo collection</p>",
				ShortInterest.class), 
			1, 0);
		
		addComponent(new TabPartGrid<IPOActivity>(
				"Collect http://nasdaq.com/ IPO activity",
				() -> nasdaqDownloadService.downloadAllIPOActivity(),
				"<p>Downloads the IPO activities from <i>www.nasdaq.com</i></p>"
				+ "<p>Visits urls, like: "
				+ "<a href=\"http://www.nasdaq.com/markets/ipos/activity.aspx?tab=pricings&month=2000-01\">http://www.nasdaq.com/markets/ipos/activity.aspx?tab=pricings&month=2000-01</a> "
				+ "and collects all the activities from there</p>"
				+ "<p>The start date is: <i>2000.01</i></p>"
				+ "<p>The data will be saved to <b>ipo_activity</b> mongo collection</p>",
				IPOActivity.class), 
			2, 0);
	}
}
