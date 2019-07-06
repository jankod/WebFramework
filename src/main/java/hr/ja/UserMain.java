package hr.ja;

import hr.ja.app.Route;
import hr.ja.app.WebFramework;
import hr.ja.app.comp.Button;
import hr.ja.app.comp.ButtonEvent;
import hr.ja.app.comp.ClickListener;
import hr.ja.app.comp.Page;
import hr.ja.app.comp.Tag;
import lombok.extern.slf4j.Slf4j;

public class UserMain {

	public static void main(String[] args) {

		WebFramework app = new WebFramework();
		app.addPage(Page1.class);

		app.start(8080);
	}
}

@Slf4j
@Route("/")
class Page1 extends Page {
	public Page1() {
		add(new Tag("h1", "Ovo je ništa"));
		add(new Tag("div", "ovo je text sadsa necu biti ovo"));
		Button btn = new Button("klikni me");
		btn.addClickListener(new ClickListener() {
			@Override
			public void onClick(ButtonEvent e) {
				log.debug("clicknuo me!!!! " + e);
			}
		});
		add(btn);
	}
}
