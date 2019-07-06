package hr.ja.app;

import java.util.List;

import spark.Request;
import spark.Session;

public class AppUtil {

	public static void setUpSession(Request req) {
		Session s = req.session(true);
		UserSession us = s.attribute(UserSession.attr_name);
		if (us == null) {
			us = new UserSession(s);
			s.attribute(UserSession.attr_name, us);
		}
	}

	public static String getPageId(org.eclipse.jetty.websocket.api.Session session) {
		List<String> pageId = session.getUpgradeRequest().getParameterMap().get("pageId");
		if(pageId == null || pageId.isEmpty()) {
			throw new RuntimeException("Canot find page id");
		}
		return pageId.get(0);
	}

}
