package hr.ja.app;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.jetty.websocket.api.Session;

import hr.ja.app.comp.Page;
import hr.ja.app.comp.actions.Action;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserPage {

	private LinkedBlockingQueue<Action> actionsQueue = new LinkedBlockingQueue<>();
//091 941 41 41 	
	private Page page;

	private HashMap<String, ListenerHolder> listeners = new HashMap<String, ListenerHolder>();

	private Session socketSession;

	public UserPage(Page page) {
		this.page = page;
	}

	public void addListener(ListenerHolder listener) {
		listeners.put(listener.id, listener);
	}

	public void onSocketConnect(Session session) {
		this.socketSession = session;
	}

	public Session getSocketSession() {
		return socketSession;
	}

	public void onSocketClose(Session session, int statusCode, String reason) {

	}

	public void onSocketMessage(Session sess, String message) {
		
	}

}
