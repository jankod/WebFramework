package hr.ja.app.comp;

public class Button extends Tag implements IClickListener {
	
	private String text;

	public Button(String text) {
		super("button");
		this.withText(text);
		this.text = text;
	}

	@Override
	public void addClickListener(ClickListener listener) {
		PageActionsBus.get().add(this, listener);
	}

//	@Override
//	public void addClickListener(ClickListener listener) {
//		PageActionsBus.get().add(this, listener);
//		clickListeners.add(listener);
//	}
	
}