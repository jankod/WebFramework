package hr.ja.app.comp.actions;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Action {

	
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter w = new StringWriter();
		try {
			mapper.writeValue(w, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w.toString();
	}
	
	public abstract String getPageId();
		
}
