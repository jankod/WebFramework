package hr.ja.app.comp;

import hr.ja.app.AppUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Page {

	private Tag bodyTag = new Tag("body");
	
	@Getter
	private String id;
	
	private static ThreadLocal<String> pageIdThreadLocal = new ThreadLocal<>();
	
	public Page() {
		this.id = AppUtil.createNewPageId();
		pageIdThreadLocal.set(this.id);
		init();
	}
	
	public static String getThreadLocalPageId() {
		return pageIdThreadLocal.get();
	}

	protected void init() {
	}

	protected void add(Tag tag) {
		if(id == null) {
			log.warn("Dodajem tag a nemam page id jošš!!!!");
		}
		bodyTag.add(tag);
	}

	public String renderBody() {
		return bodyTag.renderFormatted();
	}

}