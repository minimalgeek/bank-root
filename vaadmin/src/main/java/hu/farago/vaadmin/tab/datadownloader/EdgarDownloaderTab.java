package hu.farago.vaadmin.tab.datadownloader;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.GridLayout;

import hu.farago.data.service.EdgarDownloadService;
import hu.farago.repo.model.entity.mongo.EdgarData;
import hu.farago.vaadmin.tab.TabPartGrid;
import hu.farago.vaadmin.tab.TabPartGridInput;

@SpringComponent
@UIScope
public class EdgarDownloaderTab extends GridLayout {

	private static final long serialVersionUID = -4143512087173627823L;

	@Autowired
	private EdgarDownloadService edgarDownloadService;

	public EdgarDownloaderTab() {
		super(3, 2);
		setSizeFull();
		setMargin(true);
		setSpacing(true);

		addComponent(new TabPartGrid<EdgarData>("Collect http://sec.gov/ Edgar data",
				() -> edgarDownloadService.collectGroupContent(),
				"<p>Downloads the Edgar Data (form4, insider trading information) based on <b>US.tls</b> file</p>"
				+ "<p>Visits all the ticker's urls (e.g.: "
				+ "<a href=\"https://www.sec.gov/cgi-bin/browse-edgar?action=getcompany&CIK=AAPL&type=&dateb=&owner=include&start=40&count=40\">www.sec.gov/...</a>) "
				+ ", iterates over all the pages, iterates over all the links on the pages and collects all the files found there with <i>.xml</i> extension</p>"
				+ "<p>The data will be saved to <b>edgar_data</b> mongo collection</p>",
				EdgarData.class), 
			0, 0);

		addComponent(new TabPartGridInput<EdgarData, String>(
				"Collect http://sec.gov/ Edgar data",
				"Trading Symbol: ", 
				(e) -> edgarDownloadService.collectGroupContentFor(e), 
				"<p>Downloads the Edgar Data (form4, insider trading information) for the <b>Trading Symbol</b></p>"
				+ "<p>Visits url, like: "
				+ "<a href=\"https://www.sec.gov/cgi-bin/browse-edgar?action=getcompany&CIK=AAPL&type=&dateb=&owner=include&start=40&count=40\">www.sec.gov/...</a> "
				+ ", iterates over all the pages, then iterates over all the links on the pages and collects all the files found there with <i>.xml</i> extension</p>"
				+ "<p>The data will be saved to <b>edgar_data</b> mongo collection</p>",
				EdgarData.class,
				String.class), 
			1, 0);
		
	}
}
