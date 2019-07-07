package hr.ja.app.comp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import hr.ja.app.ListenerHolder;
import hr.ja.app.comp.actions.Action;
import hr.ja.app.comp.actions.JsEventAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageActionsBus {

	private static PageActionsBus instance = new PageActionsBus();

	private LinkedBlockingQueue<Action> actionsQueue = new LinkedBlockingQueue<>();

//	Map<String, ListenerHolder> serverLisnteners = new HashMap<>();

	public void add(Tag tag, ClickListener listener) {
		String pageId = Page.getThreadLocalPageId();
		
		ListenerHolder listenerHolder = createListener(listener, pageId);
		actionsQueue.add(new JsEventAction(tag.getId(), listenerHolder.getId(), "click", pageId));
	}

	private int listenerId = 1; // TODO: listener id start increment by page or session

	private ListenerHolder createListener(ClickListener listener, String pageId) {
		String id = listenerId++ + "";
		return new ListenerHolder(id, listener, pageId);
	}

	public LinkedBlockingQueue<Action> getActionsQueue() {
		return actionsQueue;
	}

	public static PageActionsBus get() {
		return instance;
	}

}
