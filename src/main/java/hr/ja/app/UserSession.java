package hr.ja.app;

import java.util.HashMap;

import hr.ja.app.comp.Page;
import spark.Session;

public class UserSession {

	public static String ATTRIBUTE_NAME = "us";

	private HashMap<String, UserPage> pages = new HashMap<>();
	private String id;

	private boolean isConnected;

	private org.eclipse.jetty.websocket.api.Session webSocketSession;

	UserSession(String sessionId) {
		id = sessionId;
	}

	public void addPage(Page page) {
		assert page.getId() != null : "page id is null";
		UserPage up = new UserPage(page);
		pages.put(page.getId(), up); 
	}

	public UserPage getPage(String id) {
		return pages.get(id);
	}

	public String getId() {
		return id;
	}

	public void setConnectedSocket(org.eclipse.jetty.websocket.api.Session session) {
		String pageId = AppUtil.getPageId(session);
		UserPage us = pages.get(pageId);
		assert us != null: "User page je null";
		us.setConnectedSocket(session);
	}

}
