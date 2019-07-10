package hr;

import static def.dom.Globals.alert;
import static def.dom.Globals.console;
import static def.dom.Globals.window;
import static def.jquery.Globals.jQuery;

import java.util.ArrayList;

import def.dom.WebSocket;
import def.mylib.ExternalApi;

public class ClientCode {

	@jsweet.lang.Erased
	public static native String getPageID();

	public static void main(String[] args) {

		//ExternalApi external = new ExternalApi();
		
		
		WebSocket w = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/state?pageId="
				+ window.$get("PAGE_ID"));

		java.util.List<ClientAction> actions = new ArrayList<ClientAction>();
		actions.add(new ClientAction() {

			@Override
			public String getPageId() {
				return "sadas";
			}
		});

		console.log(actions);

		w.onopen = t -> {
			console.log("Open connection");
			return null;
		};

		console.log("pocinje klijent code :))) ");
		alert("dela#");
	}
}
