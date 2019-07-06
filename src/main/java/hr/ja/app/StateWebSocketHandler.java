package hr.ja.app;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

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

	final List<Session> sessions = new ArrayList<>();
	private PageManager pageManager;

	public StateWebSocketHandler(PageManager pageManager) {
		this.pageManager = pageManager;
	}

	@OnWebSocketConnect
	public void onConnect(Session session) throws Exception {
		sessions.add(session);
		InetSocketAddress addr = session.getRemoteAddress();
		String pageId =  AppUtil.getPageId(session);
		
		log.debug("User connect: {} ima ih: {}", addr, sessions.size());
		listenServerBus();
	}

	@OnWebSocketClose
	public void onClose(Session user, int statusCode, String reason) {
		log.debug("close " + user.getRemoteAddress());
		sessions.remove(user);
	}

	@OnWebSocketMessage
	public void onMessage(Session user, String message) {
		log.debug("Dobio sam: {} ", message);
	}

	private void listenServerBus() {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					LinkedBlockingQueue<Action> q = PageActionsBus.get().getActionsQueue();
					log.debug("q ima: {}", q.size());
					Action action = null;
					try {
						action = q.take();
					} catch (InterruptedException e1) {
						log.error("", e1);
					}
					if (sessions.isEmpty()) {
						log.warn("Nema sessije ni jedne!!!");
					}
					for (Session s : sessions) {
						synchronized (s) {
							try {
								if (action != null) {
									log.debug("saljem action {}", action.toJson());
									RemoteEndpoint remote = s.getRemote();
									remote.sendString(action.toJson());
									remote.flush();
									log.debug("Poslano....");
								}
							} catch (Throwable e) {
								log.error("Error ", e.getMessage());
							}
						}
					}

				}
			}
		};

		t.setDaemon(true);
		t.start();
		log.debug("Start server for listening....");
	}

}
