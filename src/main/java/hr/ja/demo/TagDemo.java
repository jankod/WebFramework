package hr.ja.demo;

import hr.ja.app.comp.PageActionsBus;
import hr.ja.app.comp.actions.Action;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TagDemo {

	public static void main(String[] args) throws InterruptedException {
//		log.debug("start");
//		Page p1 = new Page();
//	
//		p1.add(new Tag("div"));
//		Button btn = new Button("klikni me");
//		btn.addOnClickListener(new ClickListener() {
//
//			@Override
//			public void onClick(ButtonEvent e) {
//				System.out.println("Ckliknuo sam " + e);
//			}
//		});
//		btn.withAction("neka akcija");
//		p1.add(btn);
//
//		
//		listenServerBus();
//
//		TimeUnit.SECONDS.sleep(3);
//		System.out.println(p1.render());
	}

	private static void listenServerBus() {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					Action action = PageActionsBus.get().getActionsQueue().poll();
					log.debug("dobio akciju {}", action.toJson());
				}
			}
		};

		t.setDaemon(true);
		t.start();
	}
}
