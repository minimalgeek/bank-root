package hu.farago.vaadmin.tab;

import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.themes.ValoTheme;

public class TabPartGrid<T> extends TabPartBasic {

	private static final long serialVersionUID = 7331887909881138593L;

	private Button button;
	
	private Grid response;
	private BeanItemContainer<T> container;

	public TabPartGrid(String buttonCaption, Call<T> call, String descriptionText,
			Class<T> clazz) {
		buildCommonParts(descriptionText);
		this.button = new Button(buttonCaption, e -> addContentToGrid(call.call()));
		this.button.setStyleName(ValoTheme.BUTTON_PRIMARY);
		buildGrid(clazz);
		addComponents(button, response, description);
	}

	private void buildGrid(Class<T> clazz) {
		this.response = new Grid();
		this.container = new BeanItemContainer<T>(clazz);
		this.response.setContainerDataSource(container);

		this.response.setSizeFull();
		this.response.setResponsive(true);
		this.response.setColumnReorderingAllowed(true);
		// TODO meeh, workaround for the width...
		if (this.response.getColumn("url") != null) {
			this.response.getColumn("url").setWidth(2000.0);
		}
	}
	
	public void addContentToGrid(List<T> content) {
		this.container.addAll(content);
	}

	public interface Call<T> {
		List<T> call();
	}
	
}
