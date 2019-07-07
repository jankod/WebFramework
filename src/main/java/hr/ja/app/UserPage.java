package hr.ja.app;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.jetty.websocket.api.Session;

import hr.ja.app.comp.HtmlListener;
import hr.ja.app.comp.Page;
import hr.ja.app.comp.actions.Action;
import lombok.extern.slf4j.Slf4j;
import spark.Request;

@Slf4j
public class UserPage {

	private LinkedBlockingQueue<Action> actionsQueue = new LinkedBlockingQueue<>();
	
	private Page page;

	private HashMap<String, HtmlListener> listeners = new HashMap<String, HtmlListener>();

	private Session socketSession;
	
	public UserPage(Page page) {
		this.page = page;
	}
	
	public  void addListener(HtmlListener listener) {
		
	}

	public void setConnectedSocket(Session session) {
		this.socketSession = session;
	}
	
	public Session getSocketSession() {
		return socketSession;
	}

}
