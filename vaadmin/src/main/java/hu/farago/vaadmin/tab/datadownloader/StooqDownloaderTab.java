package hu.farago.vaadmin.tab.datadownloader;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.GridLayout;

import hu.farago.vaadmin.tab.TabPartBasic;

@SpringComponent
@UIScope
public class StooqDownloaderTab extends GridLayout {

	private static final long serialVersionUID = -4143512087173627823L;

	public StooqDownloaderTab() {
		super(3, 2);
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		
		addComponent(
				new TabPartBasic("<h2>Stooq - Download missing forex data</h2>"
						+ "<h4>Every 1 hour, at HH:00</h4>"
						+ "<p>Downloads the latest forex data from <b>http://stooq.com</b></p>"
						+ "<p>The data will be saved to <b>earnings_call</b> mongo collection</p>"), 
				0, 0);
	}
}
