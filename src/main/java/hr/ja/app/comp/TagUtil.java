package hr.ja.app.comp;

public class TagUtil {

	private static int currentId = 0;

	public static String createID() {
		return currentId++ + "";
	}

}
