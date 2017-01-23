package hu.farago.vaadmin.tab;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class TabPartAction extends TabPartBasic {

	private static final long serialVersionUID = -2063689594312692427L;

	private Button button;
	
	public TabPartAction(String buttonCaption, Call call, String descriptionText) {
		buildCommonParts(descriptionText);
		this.button = new Button(buttonCaption, e -> call.call());
		this.button.setStyleName(ValoTheme.BUTTON_PRIMARY);
		addComponents(button, description);
	}

	public interface Call {
		void call();
	}
}
