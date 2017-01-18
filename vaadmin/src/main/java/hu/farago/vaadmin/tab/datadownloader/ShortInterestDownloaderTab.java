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
import hu.farago.vaadmin.tab.TabPart;

@SpringComponent
@UIScope
public class ShortInterestDownloaderTab extends GridLayout {

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

	public ShortInterestDownloaderTab() {
		super(3, 2);
		setSizeFull();
		setMargin(true);
		setSpacing(true);

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
