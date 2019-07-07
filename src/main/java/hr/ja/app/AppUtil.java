package hr.ja.app;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;

import spark.Request;
import spark.Session;

public class AppUtil {

	public static UserSession getOrCreateSession(Request req) {
		Session s = req.session(true);
		UserSession us = s.attribute(UserSession.ATTRIBUTE_NAME);
		if (us == null) {
			us = new UserSession(s.id());
			s.attribute(UserSession.ATTRIBUTE_NAME, us);
		}
		return us;
	}

	public static String getPageId(org.eclipse.jetty.websocket.api.Session session) {
		List<String> pageId = session.getUpgradeRequest().getParameterMap().get("pageId");
		if (pageId == null || pageId.isEmpty()) {
			throw new RuntimeException("Canot find page id");
		}
		return pageId.get(0);
	}

	public static UserSession getOrCreateSession(org.eclipse.jetty.websocket.api.Session session) {
		HttpSession hs = (HttpSession) session.getUpgradeRequest().getSession();
		UserSession us = (UserSession) hs.getAttribute(UserSession.ATTRIBUTE_NAME);
		if (us == null) {
			us = new UserSession(hs.getId());
			hs.setAttribute(UserSession.ATTRIBUTE_NAME, us);
		}
		return us;
	}

	public static String createNewPageId() {
		return (RandomUtils.nextLong() + "");
	}

	public static UserPage getPage(org.eclipse.jetty.websocket.api.Session sess) {
		UserSession us = AppUtil.getOrCreateSession(sess);
		String pageId = AppUtil.getPageId(sess);
		return us.getPage(pageId);
	}

	public static UserPage getPageById(String pageId) {
		
		return null;
	}

}
