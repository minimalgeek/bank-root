package hu.farago.vaadmin.tab.datadownloader;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.GridLayout;

import hu.farago.data.service.EdgarDownloadService;
import hu.farago.data.service.ForexDataDownloaderService;
import hu.farago.data.service.InsiderTradingDownloadService;
import hu.farago.data.service.MacroManService;
import hu.farago.data.service.NasdaqDownloadService;
import hu.farago.data.service.OilReportService;
import hu.farago.data.service.SAndPIndicesRefreshService;
import hu.farago.data.service.SeekingAlphaDownloadService;
import hu.farago.data.service.ServicesService;
import hu.farago.data.service.ZacksDownloadService;
import hu.farago.vaadmin.tab.ButtonDescriptionAndResponsePanel;

@SpringComponent
@UIScope
public class EdgarDownloaderTab extends GridLayout {

	private static final long serialVersionUID = -4143512087173627823L;

	@Autowired
	private EdgarDownloadService edgarDownloadService;

	@Autowired
	private ForexDataDownloaderService forexDataDownloaderService;

	@Autowired
	private InsiderTradingDownloadService insiderTradingDownloadService;

	@Autowired
	private MacroManService macroManService;

	@Autowired
	private NasdaqDownloadService nasdaqDownloadService;

	@Autowired
	private OilReportService oilReportService;

	@Autowired
	private SAndPIndicesRefreshService sAndPIndicesRefreshService;

	@Autowired
	private SeekingAlphaDownloadService seekingAlphaDownloadService;

	@Autowired
	private ServicesService servicesService;

	@Autowired
	private ZacksDownloadService zacksDownloadService;

	public EdgarDownloaderTab() {
		super(3, 2);
		setSizeFull();
		setMargin(true);
		setSpacing(true);

		addComponent(
				new ButtonDescriptionAndResponsePanel<Double>(
					"OLD - Refresh all report dates from http://zacks.com", 
					(e) -> {}, 
					"<p>Downloads the (earnings call) report dates based on the registered profiles on zacks.com</p>"
					+ "<p>Registered email address: <b>21.OCTOGON@GMAIL.COM</b></p>", 
					Double.class), 
				0, 0);
		addComponent(
				new ButtonDescriptionAndResponsePanel<Double>(
					"NEW - Refresh all report dates from http://zacks.com", 
					(e) -> {}, 
					"<p>Downloads the (earnings call) report dates based on <b>US.tls</b> file</p>"
					+ "<p>Visits all the ticker's url, like this: <a href=\"https://www.zacks.com/stock/quote/MU\">https://www.zacks.com/stock/quote/MU</a></p>", 
					Double.class), 
				1, 0);
		addComponent(
				new ButtonDescriptionAndResponsePanel<Double>("<h2>Zacks - earnings call dates downloader, 2nd implementation</h2>"
						+ "<h4>Every day, at 06:00</h4>"
						+ "<p>Based on the <b>US.tls</b> ticker file, it downloads all the earnings call dates and saves them to <b>zacks_earnings_call_dates_ii</b> mongo collection</p>"
						+ "<p>The method is the same, like in <i>NEW - Refresh all report dates from http://zacks.com</i> function</h4>"), 
				2, 0);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect http://sec.gov/ Edgar data (form4, insider trading information)", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				0, 1);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect http://sec.gov/ Edgar data (form4, insider trading information) for Trading Symbol", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				1, 1);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect http://seekingalpha.com/ data", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				0, 2);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect http://seekingalpha.com/ data for Trading Symbol", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				1, 2);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect http://seekingalpha.com/ data", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				0, 3);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect http://nasdaq.com/ short interest data", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				1, 3);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect historical short interest data", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				0, 4);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect http://nasdaq.com/ IPO activity", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				1, 4);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect https://iea.org/ oil reports tone", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				0, 5);
//		addComponent(
//				new ButtonDescriptionAndResponsePanel<Double>(
//					"Collect https://macro-man.blogspot.hu/ article and comments tone", 
//					(e) -> {}, 
//					"", 
//					Double.class), 
//				1, 5);
	}
}
