package hr.ja.app;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import hr.ja.app.comp.PageActionsBus;
import hr.ja.app.comp.actions.Action;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebSocket
public class StateWebSocketHandler {

	private PageManager pageManager;

	public StateWebSocketHandler(PageManager pageManager) {

		this.pageManager = pageManager;
		listenServerBus();
	}

	private HashMap<String, Session> connectedPageId = new HashMap<>();

	@OnWebSocketConnect
	public void onConnect(Session session) throws Exception {
		InetSocketAddress addr = session.getRemoteAddress();
		String pageId = AppUtil.getPageId(session);
		connectedPageId.put(pageId, session);

		log.debug("User connect, pageID: {}", pageId);

		// start salji evente za ovaj page id
	}

	protected void sendAction(Action action) {
		try {
			RemoteEndpoint remote = connectedPageId.get(action.getPageId()).getRemote();
			remote.sendString(action.toJson());
			
		} catch (IOException e) {
			log.error("", e);
		}
	}

	@OnWebSocketClose
	public void onClose(Session user, int statusCode, String reason) {
		log.debug("close status: {} reason: {}", statusCode, reason);
		String pageId = AppUtil.getPageId(user);
		connectedPageId.remove(pageId);
	}

	@OnWebSocketMessage
	public void onMessage(Session user, String message) {
		log.debug("Dobio sam: {} ", message);
	}

	private void listenServerBus() {
		LinkedBlockingQueue<Action> q = PageActionsBus.get().getActionsQueue();
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					if(q.isEmpty()) {
						try {
							TimeUnit.MILLISECONDS.sleep(500);
							continue;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					log.debug("q ima: {}", q.size());
					
					Action action = q.peek();
					if (action == null) {
						log.warn("action null");
						continue;

					}
					if (connectedPageId.containsKey(action.getPageId())) {
						if(!q.remove(action)) {
							log.warn("Nije uklonio akciju");
						}
						sendAction(action);
						log.debug("saljem akciju na "+ action.getPageId());
					}
					try {
						TimeUnit.MILLISECONDS.sleep(900);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		};

		t.setDaemon(true);
		t.start();
		log.debug("Start server for listening....");
	}

}
