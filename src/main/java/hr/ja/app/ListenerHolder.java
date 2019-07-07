package hr.ja.app;

import hr.ja.app.comp.HtmlListener;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListenerHolder {
	String id;
	HtmlListener listener;
	String pageId;
}