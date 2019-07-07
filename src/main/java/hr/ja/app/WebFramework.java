package hr.ja.app;

import static spark.Spark.init;
import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;

import hr.ja.app.comp.Page;
import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

@Slf4j
public class WebFramework {
	ArrayList<Class<? extends Page>> pages = new ArrayList<>();

	
	public void addPage(Class<? extends Page> page) {
		pages.add(page);
	}

	public void start(int port) {
		try {
			Spark.port(port);
			Spark.
			staticFiles.location("/public"); // Static files
			StateWebSocketHandler socketHandler = new StateWebSocketHandler();
			webSocket("/state", socketHandler);
			init();

			for (Class<? extends Page> p : pages) {
				String route = getRoutePath(p);

				Spark.get(route, (req, res) -> {
					UserSession userSession = AppUtil.getOrCreateSession(req);
					
					log.debug("evo pozvao je app");
					Page page = createNewPageInstance(p);
					userSession.addPage(page);
					String body = page.renderBody();

					Map<String, Object> model = new HashMap<>();
					model.put("pageId", page.getId());
					model.put("bodyHtml", body);
					return render(model, "index.vm");
				});
			}

			log.debug("Started on port " + port);
			Spark.awaitInitialization();
			Spark.awaitStop();
			log.debug("Stopped...");
		} catch (Throwable e) {
			log.error("", e);
			Spark.stop();
		}
	}

	private Page createNewPageInstance(Class<? extends Page> p) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<? extends Page> c = p.getConstructor();
		c.setAccessible(true);
		return c.newInstance();
	}

	private String getRoutePath(Class<? extends Page> p) {
		// log.debug("{} {}", p, p.getSuperclass());
		Route rute = p.getAnnotation(Route.class);
		assert rute != null : "Route annotation je null od page " + p;
		return rute.value();
	}

	static VelocityTemplateEngine velo = new VelocityTemplateEngine();

	public static String render(Map<String, Object> model, String templatePath) {
		return velo.render(new ModelAndView(model, templatePath));
	}

}
