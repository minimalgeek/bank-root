package hu.farago.vaadmin;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;

import hu.farago.data.service.ServicesService;
import hu.farago.repo.model.entity.mongo.AutomaticServiceError;
import hu.farago.vaadmin.tab.datadownloader.EdgarDownloaderTab;
import hu.farago.vaadmin.tab.datadownloader.OthersTab;
import hu.farago.vaadmin.tab.datadownloader.SeekingAlphaDownloaderTab;
import hu.farago.vaadmin.tab.datadownloader.ShortInterestDownloaderTab;
import hu.farago.vaadmin.tab.datadownloader.StooqDownloaderTab;
import hu.farago.vaadmin.tab.datadownloader.ZacksDownloaderTab;

@SpringUI
@Theme("mytheme")
@Title("OTP Admin")
public class AdminUI extends UI {

	private static final long serialVersionUID = -7946117643288515312L;

	private TabSheet tabSheet;
	private VerticalSplitPanel vsp;
	private Grid responseGrid;
	private BeanItemContainer<AutomaticServiceError> responses;
	private AbsoluteLayout responseLayout;
	private Button automaticServiceRefreshButton;
	
	@Autowired
	private ServicesService servicesService;
	
	@Autowired
	private EdgarDownloaderTab edgarDownloaderTab;
		
	@Autowired
	private OthersTab othersTab;
	
	@Autowired
	private SeekingAlphaDownloaderTab seekingAlphaDownloaderTab;
	
	@Autowired
	private ShortInterestDownloaderTab shortInterestDownloaderTab;
	
	@Autowired
	private StooqDownloaderTab stooqDownloaderTab;
	
	@Autowired
	private ZacksDownloaderTab zacksDownloaderTab;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		VaadinSession.getCurrent().getSession().setMaxInactiveInterval(-1);
		buildTabs();
		setupResponseGrid();

		vsp = new VerticalSplitPanel(tabSheet, responseLayout);
		vsp.setSplitPosition(80, Unit.PERCENTAGE);
		
		setContent(vsp);
	}

	private void buildTabs() {
		tabSheet = new TabSheet();

		tabSheet.addTab(zacksDownloaderTab, "Zacks Downloader", new ThemeResource("img/planets/01.png"));
		tabSheet.addTab(seekingAlphaDownloaderTab, "SeekingAlpha Downloader", new ThemeResource("img/planets/02.png"));
		tabSheet.addTab(edgarDownloaderTab, "Edgar Downloader", new ThemeResource("img/planets/03.png"));
		tabSheet.addTab(shortInterestDownloaderTab, "Short Interest Downloader", new ThemeResource("img/planets/04.png"));
		tabSheet.addTab(stooqDownloaderTab, "Stooq Downloader", new ThemeResource("img/planets/05.png"));
		// Mongo, Spice Indices, Yahoo
		tabSheet.addTab(othersTab, "Others", new ThemeResource("img/planets/06.png"));
	}
	
	private void setupResponseGrid() {
		responses = new BeanItemContainer<AutomaticServiceError>(AutomaticServiceError.class, Lists.newArrayList());
		responseGrid = new Grid();
		responseGrid.setSizeFull();
		responseGrid.setContainerDataSource(responses);
		responseGrid.setColumnReorderingAllowed(true);
		responseGrid.setDescription("Service Errors");
		responseGrid.removeColumn("id");
		responseGrid.removeColumn("dateTime");
		
		automaticServiceRefreshButton = new Button("Refresh");
		automaticServiceRefreshButton.addClickListener((e) -> {
				responses.removeAllItems();
				responses.addAll(servicesService.getErrors());
			});
		
		responseLayout = new AbsoluteLayout();
		responseLayout.setSizeFull();
		responseLayout.addComponent(responseGrid);
		responseLayout.addComponent(automaticServiceRefreshButton, "bottom: 5px; right: 5px; z-index: 1000");
	}

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AdminUI.class)
	public static class AdminUIServlet extends SpringVaadinServlet {
		private static final long serialVersionUID = -1990269999000772222L;
	}

}
