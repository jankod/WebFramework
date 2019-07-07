package hr.ja.app.event;

import hr.ja.app.comp.Tag;

// 
//@DomEvent("click")
public class ClickEvent<C extends Tag> extends TagEvent<C> {

	private static final long serialVersionUID = 1L;
	public ClickEvent(Object source) {
		super(source);
	}


}
