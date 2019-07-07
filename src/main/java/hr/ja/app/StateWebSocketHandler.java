package hr.ja.app;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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



	public StateWebSocketHandler() {
		listenServerBus();
	}


	@OnWebSocketConnect
	public void onConnect(Session session) throws Exception {
		UserPage userPage = AppUtil.getPage(session);
		userPage.onSocketConnect(session);
	}

	protected void sendAction(Action action) {
//			RemoteEndpoint remote = connectedPageId.get(action.getPageId()).getRemote();
//			remote.sendString(action.toJson());
	}

	@OnWebSocketClose
	public void onClose(Session sess, int statusCode, String reason) {
		log.debug("close status: {} reason: {}", statusCode, reason);
		UserPage userPage = AppUtil.getPage(sess);		
		userPage.onSocketClose(sess, statusCode, reason);
	}

	@OnWebSocketMessage
	public void onMessage(Session sess, String message) {
		UserPage userPage = AppUtil.getPage(sess);
		userPage.onSocketMessage(sess, message);
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
					
					UserPage userPage = AppUtil.getPageById(action.getPageId());
					
//					if (connectedPageId.containsKey(action.getPageId())) {
//						if(!q.remove(action)) {
//							log.warn("Nije uklonio akciju");
//						}
//						sendAction(action);
//						log.debug("saljem akciju na "+ action.getPageId());
//					}
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
