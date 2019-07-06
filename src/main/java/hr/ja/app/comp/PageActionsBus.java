package hr.ja.app.comp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import hr.ja.app.comp.actions.Action;
import hr.ja.app.comp.actions.JsEventAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageActionsBus {

	private static PageActionsBus instance = new PageActionsBus();

	private LinkedBlockingQueue<Action> actionsQueue = new LinkedBlockingQueue<>();

	Map<String, ListenerHolder> serverLisnteners = new HashMap<>();

	public void add(Tag tag, ClickListener listener, String pageId) {
		String serverListenerId = addListener(listener);
		actionsQueue.add(new JsEventAction(tag.getId(), serverListenerId, "click", pageId));
	}

	private int listenerId = 1; // TODO: listener id start increment by page session of user session

	private String addListener(ClickListener listener) {
		String id = listenerId++ + "";
		serverLisnteners.put(id, new ListenerHolder(id, listener));
		return id;
	}

	public LinkedBlockingQueue<Action> getActionsQueue() {
		return actionsQueue;
	}

	public static PageActionsBus get() {
		return instance;
	}
}

@Data
@AllArgsConstructor
class ListenerHolder {
	String serverListenerId;
	HtmlListeners listener;
}
