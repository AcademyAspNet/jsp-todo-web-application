package web.application.helper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FormatHelper {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	public static String formatDateTime(Timestamp timestamp) {
		return dateFormat.format(timestamp);
	}
}
