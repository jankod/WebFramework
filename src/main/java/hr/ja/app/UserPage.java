package hr.ja.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.jetty.websocket.api.Session;

import hr.ClientAction;
import hr.JsEventAction;
import hr.ja.app.comp.ClickListener;
import hr.ja.app.comp.Page;
import hr.ja.app.comp.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserPage {

	@Getter
	@Setter
	private Page page;

	@Getter
	private String id;

	@Getter
	@Setter
	private UserSession userSession;

	private HashMap<String, ListenerHolder> listeners = new HashMap<String, ListenerHolder>();

	private Session socketSession;

	private LinkedBlockingQueue<ClientAction> actionsQueue = new LinkedBlockingQueue<>();

	private boolean socketConnected = false;

	private static final ThreadLocal<String> threadLocalPageId = new ThreadLocal<String>();

	private static Map<String, UserPage> globalPages = new HashMap<String, UserPage>();
	
	public UserPage() {
		this.id = AppUtil.createNewPageId();
		threadLocalPageId.set(this.id);
		
		globalPages.put(this.id, this);
	}
	
	public static UserPage getPage(String id) {
		return globalPages.get(id);
	}

	public void addAction(JsEventAction action) {
		actionsQueue.add(action);
		startListen();
	}

	public static String getThreadLocalPageId() {
		return threadLocalPageId.get();
	}

	public void addListener(ListenerHolder listener) {
		listeners.put(listener.id, listener);
	}

	public void onSocketConnect(Session session) {
		this.socketSession = session;
		socketConnected = true;
		startListen();

	}

	public Session getSocketSession() {
		return socketSession;
	}

	public void onSocketClose(Session session, int statusCode, String reason) {
		socketConnected = false;
	}

	public void onSocketMessage(Session sess, String message) {

	}
	
	public static void add(Tag tag, ClickListener listener) {
		String pageId = UserPage.getThreadLocalPageId();
		ListenerHolder listenerHolder = createListener(listener, pageId);
		JsEventAction action = new JsEventAction(tag.getId(), listenerHolder.getId(), "click", pageId);
		AppUtil.getPageById(pageId).addAction(action);
		
	}

	private static int listenerId = 1; // TODO: listener id start increment by page or session

	private static ListenerHolder createListener(ClickListener listener, String pageId) {
		String id = listenerId++ + "";
		return new ListenerHolder(id, listener, pageId);
	}

	private void startListen() {
		ClientAction a = actionsQueue.poll();
		if (a != null) {
			if (socketConnected) {
//				try {
//					// socketSession.getRemote().sendString(a);
//				} catch (IOException e) {
//					log.error("", e);
//				}
			} else {
				log.warn("Not connected???? not send action????");
			}
		}
	}

}
