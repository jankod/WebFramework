package hr.ja.app.comp;

import java.util.ArrayList;
import java.util.List;

public class Button extends Tag implements IClickListener {
	private String text;
	private List<ClickListener> clickListeners = new ArrayList<>();

	public Button(String text) {
		super("button");
		this.withText(text);
		this.text = text;
	}

	@Override
	public void addClickListener(ClickListener listener, String pageId) {
		PageActionsBus.get().add(this, listener, pageId);
		clickListeners.add(listener);
	}
}