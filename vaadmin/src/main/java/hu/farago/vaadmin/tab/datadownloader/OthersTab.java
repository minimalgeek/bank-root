package hu.farago.vaadmin.tab.datadownloader;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.GridLayout;

import hu.farago.data.service.MacroManService;
import hu.farago.data.service.OilReportService;
import hu.farago.vaadmin.tab.block.TabPartAction;
import hu.farago.vaadmin.tab.block.TabPartBasic;

@SpringComponent
@UIScope
public class OthersTab extends GridLayout {

	private static final long serialVersionUID = -4143512087173627823L;

	@Autowired
	private OilReportService oilReportService;

	@Autowired
	private MacroManService macroManService;

	public OthersTab() {
		super(3, 2);
		setSizeFull();
		setMargin(true);
		setSpacing(true);

		addComponent(new TabPartBasic("<h2>Yahoo - Download tick forex data</h2>"
				+ "<h4>Every 5 seconds - at the moment it's turned off!</h4>"
				+ "<p>Downloads the latest forex data from <b>Yahoo Finance</b></p>"
				+ "<p>The data will be saved to <b>forex</b> mongo collection</p>"), 0, 0);

		addComponent(
				new TabPartBasic("<h2>Spice Indices - Download S&P members</h2>" + "<h4>Every day, at 00:00</h4>"
						+ "<p>Checks for changes in the S&P 100, 400, 500, 600 lists on <b>https://www.spice-indices.com</b>, and saves them to <b>z:/Quant_CO2/SAndP/</b></p>"
						+ "<p>The generated <b>US.tls</b> file is very important, it is used in several services as the main ticker source</p>"),
				1, 0);

		addComponent(
				new TabPartBasic("<h2>MongoDB - Create flat collections</h2>" + "<h4>Every day, at 08:00</h4>"
						+ "<p>We check the JavaScript (xyz.js) files of <b>z:/Quant_CO2/mongo_scripts/</b>, and run them on MongoDB, <b>insider</b> database</p>"
						+ "<p>It is necessary for the <b>AmiBroker - MongoDB Bridge</b>, because AmiBroker can not work on complex objects</p>"),
				2, 0);

		addComponent(new TabPartAction("Collect https://iea.org/ oil reports tone",
				() -> oilReportService.downloadOilReports(),
				"<p>Collects all the reports between 2007 and 2016, then split them by sections for further textual tone processing</p>"
						+ "<p>The tone processing is based on Henry Words</p>"
						+ "<p>The data will be saved to <b>oil_report</b> mongo collection</p>"),
				0, 1);

		addComponent(new TabPartAction("Collect https://macro-man.blogspot.hu/ article and comments tone",
				() -> macroManService.downloadMacroMans(),
				"<p>Collects all the articles and comments between 2006 and 2016 for further textual tone processing</p>"
						+ "<p>The tone processing is based on Henry Words</p>"
						+ "<p>The data will be saved to <b>macro_man</b> mongo collection</p>"),
				1, 1);
	}
}
