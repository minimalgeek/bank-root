package hu.farago.vaadmin.tab.datadownloader;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.GridLayout;

import hu.farago.data.service.ZacksDownloadService;
import hu.farago.data.service.dto.URLSuccess;
import hu.farago.repo.model.entity.mongo.ZacksEarningsCallDates2;
import hu.farago.vaadmin.tab.TabPart;
import hu.farago.vaadmin.tab.TabPartBasic;

@SpringComponent
@UIScope
public class ZacksDownloaderTab extends GridLayout {

	private static final long serialVersionUID = -4143512087173627823L;

	@Autowired
	private ZacksDownloadService zacksDownloadService;

	public ZacksDownloaderTab() {
		super(3, 1);
		setSizeFull();
		setMargin(true);
		setSpacing(true);

		addComponent(
				new TabPart<URLSuccess>(
					"OLD - Refresh all report dates from http://zacks.com", 
					() -> zacksDownloadService.refreshAllReportDates(), 
					"<p>Downloads the (earnings call) report dates based on the registered profiles on zacks.com</p>"
					+ "<p>Registered email address: <b>21.OCTOGON@GMAIL.COM</b></p>", 
					URLSuccess.class), 
				0, 0);
		
		addComponent(
				new TabPart<ZacksEarningsCallDates2>(
					"NEW - Refresh all report dates from http://zacks.com", 
					() -> zacksDownloadService.downloadAllZECD(), 
					"<p>Downloads the (earnings call) report dates based on <b>US.tls</b> file</p>"
					+ "<p>Visits all the ticker's url, like this: <a href=\"https://www.zacks.com/stock/quote/MU\">https://www.zacks.com/stock/quote/MU</a></p>", 
					ZacksEarningsCallDates2.class), 
				1, 0);
		addComponent(
				new TabPartBasic("<h2>Zacks - earnings call dates downloader, 2nd implementation</h2>"
						+ "<h4>Every day, at 06:00</h4>"
						+ "<p>Based on the <b>US.tls</b> ticker file, it downloads all the earnings call dates and saves them to <b>zacks_earnings_call_dates_ii</b> mongo collection</p>"
						+ "<p>The method is the same, like in <i>NEW - Refresh all report dates from http://zacks.com</i> function</h4>"), 
				2, 0);
	}
}
