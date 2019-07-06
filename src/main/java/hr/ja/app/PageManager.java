package hr.ja.app;

import java.util.HashMap;

import hr.ja.app.comp.Page;
import lombok.extern.slf4j.Slf4j;
import spark.Request;


@Slf4j
public class PageManager {

	private HashMap<String, Page> pages = new HashMap<>();

	public void addPage(Page page, Request req) {
		this.pages.put(page.getId(), page);
		String sessId = req.session(true).id();
		log.debug("session id {} page id {}", sessId, page.getId());
	}

}
