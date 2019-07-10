package hr;

import lombok.Data;

@Data
public class JsEventAction extends ClientAction {

	private String eleId;
	private String serverListenerId;
	private String actionName;
	private String pageId;

	public JsEventAction(String eleId, String serverListenerId, String actionName, String pageId) {
		assert pageId != null: "Page id is null";
		this.eleId = eleId;
		this.serverListenerId = serverListenerId;
		this.actionName = actionName;
		this.pageId = pageId;
	}

	@Override
	public String getPageId() {
		return pageId;
	}

}
