package hr.ja.app.comp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Page {

	private Tag bodyTag = new Tag("body");
	
	public Page() {
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