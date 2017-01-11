package hu.farago.vaadmin.tab;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ButtonDescriptionAndResponsePanel<T> extends VerticalLayout {

	private static final long serialVersionUID = 7331887909881138593L;

	private Button button;
	private Label description;
	private Grid response;
	private BeanItemContainer<T> container;

	public ButtonDescriptionAndResponsePanel(String descriptionText) {
		this.description = new Label(descriptionText, ContentMode.HTML);
		this.description.setSizeFull();
		addComponents(description);
		this.setStyleName("bdr_panel");
		this.setMargin(true);
	}

	public ButtonDescriptionAndResponsePanel(String buttonCaption, ClickListener listener, String descriptionText,
			Class<T> clazz) {
		this.description = new Label(descriptionText, ContentMode.HTML);
		this.description.setSizeFull();
		this.button = new Button(buttonCaption, listener);
		this.button.setStyleName(ValoTheme.BUTTON_PRIMARY);
		buildGrid(clazz);
		
		addComponents(button, description, response);

		this.setStyleName("bdr_panel");
		this.setMargin(true);
	}

	private void buildGrid(Class<T> clazz) {
		this.response = new Grid();
		this.container = new BeanItemContainer<T>(clazz);
		this.response.setContainerDataSource(container);

		this.response.setSizeFull();
		this.response.setResponsive(true);
		this.response.setColumnReorderingAllowed(true);
	}

}
