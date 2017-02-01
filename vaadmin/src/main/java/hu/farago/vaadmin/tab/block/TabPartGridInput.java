package hu.farago.vaadmin.tab.block;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class TabPartGridInput<T> extends TabPartBasic {

	private static final long serialVersionUID = 5650712561772147910L;

	private Button button;
	private Grid response;
	private BeanItemContainer<T> container;
	private List<InputBlock<?>> inputs;
	
	public static class InputBlock<C> {
		private TextField input;
		
		public InputBlock(String caption, Class<C> clazz) {
			this.input = new TextField(caption);
			this.input.setConverter(clazz);
			this.input.setNullRepresentation("");
		}
		
		@SuppressWarnings("unchecked")
		public C getConvertedValue() {
			return (C) this.input.getConvertedValue();
		}
	}

	public TabPartGridInput(String buttonCaption, Call<T> call, String descriptionText,
			Class<T> clazz, List<InputBlock<?>> inputs) {
		buildCommonParts(descriptionText);
		this.button = new Button(buttonCaption, e -> {
			Object[] params = 
					this.inputs.stream().map(c -> c.getConvertedValue()).collect(Collectors.toList()).toArray();
			List<T> retList = call.call(params);
			addContentToGrid(retList);
		});
		this.button.setStyleName(ValoTheme.BUTTON_PRIMARY);
		buildGrid(clazz);
		this.inputs = inputs;
		for (InputBlock<?> block : inputs) {
			addComponent(block.input);
		}
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
		this.container.removeAllItems();
		this.container.addAll(content);
	}

	public interface Call<T> {
		List<T> call(Object... params);
	}

}
