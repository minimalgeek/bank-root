package hu.farago.vaadmin.tab.datadownloader;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.GridLayout;

import hu.farago.data.service.SeekingAlphaDownloadService;
import hu.farago.data.service.SeekingAlphaDownloadService.EarningsCallView;
import hu.farago.vaadmin.tab.block.TabPartBasic;
import hu.farago.vaadmin.tab.block.TabPartGrid;
import hu.farago.vaadmin.tab.block.TabPartGridInput;
import hu.farago.vaadmin.tab.block.TabPartGridInput.InputBlock;

@SpringComponent
@UIScope
public class SeekingAlphaDownloaderTab extends GridLayout {

	private static final long serialVersionUID = -4143512087173627823L;

	@Autowired
	private SeekingAlphaDownloadService seekingAlphaDownloadService;

	public SeekingAlphaDownloaderTab() {
		super(3, 2);
		setSizeFull();
		setMargin(true);
		setSpacing(true);

		addComponent(new TabPartGrid<EarningsCallView>("Collect http://seekingalpha.com/ data",
				() -> seekingAlphaDownloadService.collectEarningsCalls(),
				"<p>Downloads the earnings call transcripts based on <b>US.tls</b> file</p>"
						+ "<p>Visits all the ticker's url (e.g.: "
						+ "<a href=\"http://seekingalpha.com/symbol/AAPL/earnings/transcripts\">http://seekingalpha.com/symbol/AAPL/earnings/transcripts</a>) "
						+ "and collects all the transcripts from the links presented there</p>"
						+ "<p>The data will be saved to <b>earnings_call</b> mongo collection</p>",
				EarningsCallView.class), 0, 0);

		addComponent(new TabPartGridInput<EarningsCallView>("Collect http://seekingalpha.com/ data",
				(e) -> seekingAlphaDownloadService.collectEarningsCallsFor((String) e[0]),
				"<p>Downloads the earnings call transcripts for the <b>Trading Symbol</b></p>" + "<p>Visits url, like: "
						+ "<a href=\"http://seekingalpha.com/symbol/AAPL/earnings/transcripts\">http://seekingalpha.com/symbol/AAPL/earnings/transcripts</a> "
						+ "and collects all the transcripts from the links presented there</p>"
						+ "<p>The data will be saved to <b>earnings_call</b> mongo collection</p>",
				EarningsCallView.class, Lists.newArrayList(new InputBlock<String>("Trading Symbol: ", String.class))),
				1, 0);

		addComponent(
				new TabPartGridInput<EarningsCallView>("Collect http://seekingalpha.com/ data",
						(e) -> seekingAlphaDownloadService.collectLastNTranscripts((Integer) e[0]),
						"<p>Downloads the <b>'N' freshest</b> earnings call transcripts based on <b>US.tls</b> file</p>"
								+ "<p>Visits all the ticker's url (e.g.: "
								+ "<a href=\"http://seekingalpha.com/symbol/AAPL/earnings/transcripts\">http://seekingalpha.com/symbol/AAPL/earnings/transcripts</a>) "
								+ "and collects the top N transcripts from the links presented there</p>"
								+ "<p>The data will be saved to <b>earnings_call</b> mongo collection</p>",
						EarningsCallView.class,
						Lists.newArrayList(new InputBlock<String>("Number of fresh transcripts: ", String.class))),
				2, 0);

		addComponent(new TabPartGridInput<EarningsCallView>("Collect http://seekingalpha.com/ data",
				(e) -> seekingAlphaDownloadService.collectLastNTranscriptsFor((Integer) e[0], (String) e[1]),
				"<p>Downloads the <b>'N' freshest</b> earnings call transcripts for the <b>Trading Symbol</b></p>"
						+ "<p>Visits url, like: "
						+ "<a href=\"http://seekingalpha.com/symbol/AAPL/earnings/transcripts\">http://seekingalpha.com/symbol/AAPL/earnings/transcripts</a>) "
						+ "and collects the top N transcripts from the links presented there</p>"
						+ "<p>The data will be saved to <b>earnings_call</b> mongo collection</p>",
				EarningsCallView.class,
				Lists.newArrayList(new InputBlock<Integer>("Number of fresh transcripts: ", Integer.class),
						new InputBlock<String>("Trading Symbol: ", String.class))),
				0, 1);

		addComponent(new TabPartGrid<EarningsCallView>("Import all Earning Call files",
				() -> seekingAlphaDownloadService.importAllFiles(),
				"<p>Imports all the Earnings Call from <b>z:/Quant_CO2/earnings_call/</b> directory</p>"
						+ "<p>Filename looks like: <i>AMLN_2002Q4_20030220_1600.txt</i></p>"
						+ "<p>The data will be saved to <b>earnings_call</b> mongo collection</p>",
				EarningsCallView.class), 1, 1);

		addComponent(
				new TabPartBasic("<h2>SeekingAlpha and Zacks integration</h2>" + "<h4>Every day, at 00:00</h4>"
						+ "<p>When we find a future <b>report date</b> on <b>http://zacks.com</b>, we save it to <b>zacks_earnings_call_dates</b></p>"
						+ "<p>At the same time, it schedules 4 jobs for the following 4 days, "
						+ "to check arriving Earnings Call Transcripts on <b>http://seekingalpha.com/</b></p>"),
				2, 1);
	}
}
