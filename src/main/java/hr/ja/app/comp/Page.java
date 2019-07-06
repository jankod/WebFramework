package hr.ja.app.comp;

import org.apache.commons.lang.math.RandomUtils;

import lombok.Getter;

public abstract class Page {

	private Tag bodyTag = new Tag("body");
	
	@Getter
	private String id;

	public Page() {
		this.id = (RandomUtils.nextLong() + "");
		init();
	}

	protected void init() {

	}

	protected void add(Tag tag) {
		bodyTag.add(tag);
	}

	public String renderBody() {
		return bodyTag.renderFormatted();
	}

}