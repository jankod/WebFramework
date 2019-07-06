package hr.ja.app.comp;

import elemental2.dom.EventListener;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

public class Tag extends ContainerTag {

//	String pageId;
	private String id;

	public Tag(String tagName) {
		super(tagName);
		this.id = TagUtil.createID();
		withId(this.id);
	}

	public Tag(String tagName, String text) {
		this(tagName);
		withText(text);
	}

	public void add(Tag tag) {
		this.with(tag);
		addPageId(tag);

		if (tag.getClass().isAssignableFrom(EventListener.class)) {
			EventListener e = (EventListener) tag;
		}
	}
	
	@Override
	public ContainerTag withText(String text) {
		return super.withText(text);
	}

	private void addPageId(Tag tag) {
//		if (tag.getPageId() == null) {
//			tag.pageId = this.pageId;
//		}
	}

	public String getId() {
		return id;
	}

//	public String getPageId() {
//		return pageId;
//	}

}
