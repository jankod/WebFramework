package hr.ja.app.comp;

import java.util.concurrent.LinkedBlockingQueue;

import hr.ClientAction;
import hr.JsEventAction;
import hr.ja.app.AppUtil;
import hr.ja.app.ListenerHolder;
import hr.ja.app.UserPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageActionsBus {

	private static PageActionsBus instance = new PageActionsBus();


	public void add(Tag tag, ClickListener listener) {
		String pageId = UserPage.getThreadLocalPageId();
		ListenerHolder listenerHolder = createListener(listener, pageId);
		JsEventAction action = new JsEventAction(tag.getId(), listenerHolder.getId(), "click", pageId);
		AppUtil.getPageById(pageId).addAction(action);
		
	}

	private int listenerId = 1; // TODO: listener id start increment by page or session

	private ListenerHolder createListener(ClickListener listener, String pageId) {
		String id = listenerId++ + "";
		return new ListenerHolder(id, listener, pageId);
	}

	public static PageActionsBus get() {
		return instance;
	}
}
