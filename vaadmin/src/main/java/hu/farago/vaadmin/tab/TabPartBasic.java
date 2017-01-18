package hu.farago.vaadmin.tab;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class TabPartBasic extends VerticalLayout {

	private static final long serialVersionUID = 4412883691014701332L;

	protected Label description;
	
	public TabPartBasic(){
	}
	
	public TabPartBasic(String descriptionText) {
		buildCommonParts(descriptionText);
		addComponents(description);
	}
	
	protected void buildCommonParts(String descriptionText) {
		this.description = new Label(descriptionText, ContentMode.HTML);
		this.description.setSizeFull();
		this.setStyleName("bdr_panel");
		this.setMargin(true);
		this.setSpacing(true);
	}
}
