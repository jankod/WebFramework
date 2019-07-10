package hr.ja.app;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import hr.ClientAction;
import hr.ja.app.comp.PageActionsBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebSocket
public class GlobalWebSocketHandler {

	public GlobalWebSocketHandler() {
	}

	@OnWebSocketConnect
	public void onConnect(Session session) throws Exception {
		UserPage userPage = AppUtil.getPage(session);
		userPage.onSocketConnect(session);
	}

	protected void sendAction(ClientAction action) {
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

}
