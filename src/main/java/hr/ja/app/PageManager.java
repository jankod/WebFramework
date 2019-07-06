package hr.ja.app;

import java.util.HashMap;

import hr.ja.app.comp.Page;
import lombok.extern.slf4j.Slf4j;
import spark.Request;

@Slf4j
public class PageManager {

	private HashMap<String, Page> pageIdPage = new HashMap<>();

	public void addPage(Page page, Request req) {
		this.pageIdPage.put(page.getId(), page);
		String sessId = req.session(true).id();
		log.debug("session id {} page id {}", sessId, page.getId());
		// TODO: verify if session Id is owner of page id...
	}

	public Page getPage(String pageId) {
		Page page = pageIdPage.get(pageId);
		if (page == null) {
			log.warn("Page not fond! id: {}", pageId);
		}
		return page;

	}

}
