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
import hu.farago.vaadmin.tab.TabPart;
import hu.farago.vaadmin.tab.TabPartWithInput;

@SpringComponent
@UIScope
public class SeekingAlphaDownloaderTab extends GridLayout {

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

	public SeekingAlphaDownloaderTab() {
		super(3, 1);
		setSizeFull();
		setMargin(true);
		setSpacing(true);

		addComponent(new TabPart<String>(
				"Collect http://seekingalpha.com/ data",
				() -> seekingAlphaDownloadService.collectEarningsCalls(),
				"<p>Downloads the earnings call transcripts based on <b>US.tls</b> file"
						+ "<p>Visits all the ticker's url (e.g.: "
						+ "<a href=\"http://seekingalpha.com/symbol/AAPL/earnings/transcripts\">http://seekingalpha.com/symbol/AAPL/earnings/transcripts</a>) "
						+ "and collects all the transcripts from the links presented there</p>"
						+ "<p>The data will be saved to <b>earnings_call</b> mongo collection</p>",
				String.class), 
				0, 0);

		addComponent(new TabPartWithInput<String, String>(
				"Collect http://seekingalpha.com/ data",
				"Trading Symbol: ", 
				(e) -> seekingAlphaDownloadService.collectEarningsCallsFor(e), 
				"<p>Downloads the earnings call transcripts for the <b>Trading Symbol</b></p>", 
				String.class,
				String.class), 
				1, 0);

	}
}
