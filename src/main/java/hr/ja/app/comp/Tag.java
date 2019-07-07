package hr.ja.app.comp;

import java.util.ArrayList;
import java.util.List;

import elemental2.dom.EventListener;
import j2html.tags.ContainerTag;

public class Tag {

	private List<Tag> children = new ArrayList<>();
	private ContainerTag ct;
	private String id;

	public Tag(String tagName) {
		ct = new ContainerTag(tagName);
		this.id = TagUtil.createID();
		ct.withId(this.id);
	}

	public Tag(String tagName, String text) {
		this(tagName);
		withText(text);
	}

	public Tag add(Tag tag) {
		ct.with(tag.ct);
		this.children.add(tag);
		return this;
	}

	public Tag withText(String text) {
		ct.withText(text);
		return this;
	}

	public String getId() {
		return id;
	}

	public String renderFormatted() {
		return ct.renderFormatted();
	}

}
