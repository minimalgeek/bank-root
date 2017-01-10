package hu.farago.vaadmin;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

import hu.farago.vaadmin.tab.datadownloader.DataDownloaderTab;

@SpringUI
@Theme("mytheme")
@Title("OTP Admin")
public class AdminUI extends UI {

	private static final long serialVersionUID = -7946117643288515312L;

	private TabSheet tabSheet;

	@Autowired
	private DataDownloaderTab dataDownloaderTab;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		VaadinSession.getCurrent().getSession().setMaxInactiveInterval(-1);
		buildTabs();

		setContent(tabSheet);
	}

	private void buildTabs() {
		tabSheet = new TabSheet();

		tabSheet.addTab(dataDownloaderTab, "Data Downloader", new ThemeResource("img/planets/01.png"));
	}

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AdminUI.class)
	public static class AdminUIServlet extends SpringVaadinServlet {
		private static final long serialVersionUID = -1990269999000772222L;
	}

}
