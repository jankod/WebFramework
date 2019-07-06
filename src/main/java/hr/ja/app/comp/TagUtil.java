package hr.ja.app.comp;

public class TagUtil {

	private static int currentId = 1;

	public static String createID() {
		return currentId++ + "";
	}

}
