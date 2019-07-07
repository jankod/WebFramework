package hr.ja.app.event;

import java.util.EventObject;

import hr.ja.app.comp.Tag;


// public class ComponentEvent<T extends Component> extends EventObject {
public class TagEvent <T extends Tag> extends EventObject {

	public TagEvent(Object source) {
		super(source);
	}

	private static final long serialVersionUID = 1L;

}
