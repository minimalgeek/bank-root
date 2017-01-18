package hu.farago.vaadmin.tab;

import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class TabPartWithInput<T, S> extends TabPartBasic {

	private static final long serialVersionUID = 5650712561772147910L;

	private TextField input;
	private Button button;
	private Grid response;
	private BeanItemContainer<T> container;

	public TabPartWithInput(String buttonCaption, String inputCaption,
			Call<T, S> call, String descriptionText, Class<T> clazz,
			Class<S> inputClazz) {
		buildCommonParts(descriptionText);
		this.input = new TextField(inputCaption);
		this.input.setConverter(inputClazz);
		this.button = new Button(buttonCaption, e -> addContentToGrid(call.call((S) this.input.getConvertedValue())));
		this.button.setStyleName(ValoTheme.BUTTON_PRIMARY);
		buildGrid(clazz);
		addComponents(input, button, response, description);
	}

	private void buildGrid(Class<T> clazz) {
		this.response = new Grid();
		this.container = new BeanItemContainer<T>(clazz);
		this.response.setContainerDataSource(container);

		this.response.setSizeFull();
		this.response.setResponsive(true);
		this.response.setColumnReorderingAllowed(true);
	}

	public void addContentToGrid(List<T> content) {
		this.container.addAll(content);
	}

	public interface Call<T, S> {
		List<T> call(S param);
	}

}
