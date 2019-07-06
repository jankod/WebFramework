package hr.ja.app.comp;

import elemental2.dom.EventListener;
import j2html.tags.ContainerTag;

public class Tag extends ContainerTag {

	private String id;

	public Tag(String tagName) {
		super(tagName);
		
		id = TagUtil.createID();
		withId(id);
	}

	public Tag(String tagName, String text) {
		this(tagName);
		withText(text);
	}

	public void add(Tag tag) {
		this.with(tag);
		if(tag.getClass().isAssignableFrom(EventListener.class)) {
			EventListener e = (EventListener) tag;
		}
	}
	
	public String getId() {
		return id;
	}

}
