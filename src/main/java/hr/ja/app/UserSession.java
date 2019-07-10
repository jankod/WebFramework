package hr.ja.app;

import java.util.HashMap;

import hr.ja.app.comp.Page;

public class UserSession {

	public static String ATTRIBUTE_NAME = "us";

	private HashMap<String, UserPage> idPages = new HashMap<>();
	
	private String id;
	
	

	UserSession(String sessionId) {
		id = sessionId;
	}

	public void addPage(UserPage page) {
		idPages.put(page.getId(), page);
	}

	public UserPage getPage(String id) {
		return idPages.get(id);
	}

	public String getId() {
		return id;
	}

}
