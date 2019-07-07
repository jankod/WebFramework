package hr.ja.app.event;

import java.io.Serializable;
import java.util.EventListener;

@FunctionalInterface
public interface TagEventListener<T extends TagEvent<?>> extends EventListener, Serializable {

	/**
	 * Invoked when a component event has been fired.
	 *
	 * @param event component event
	 */
	void onTagEvent(T event);
}
