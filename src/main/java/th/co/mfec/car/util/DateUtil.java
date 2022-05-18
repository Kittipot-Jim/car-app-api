package th.co.mfec.car.util;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final SimpleDateFormat rsDTTMFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	public static String rsDTTMFormat(Date date) {
		return rsDTTMFormat.format(date);
	}

	public static String rsDTTMFormat(Timestamp timestamp) {
		return rsDTTMFormat.format(timestamp);
	}

}
