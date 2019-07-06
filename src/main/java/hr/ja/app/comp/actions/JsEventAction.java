package hr.ja.app.comp.actions;

import lombok.Data;

@Data
public class JsEventAction extends Action {

	private String eleId;
	private String serverListenerId;
	private String actionName;

	public JsEventAction(String eleId, String serverListenerId, String actionName) {
		this.eleId = eleId;
		this.serverListenerId = serverListenerId;
		this.actionName = actionName;
	}

}
