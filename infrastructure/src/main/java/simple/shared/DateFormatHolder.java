package simple.shared;

import java.text.DateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

public class DateFormatHolder {

	public static DateFormat get() {
		DateFormat df = new ISO8601DateFormat();
		df.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Brazil/East")));
		return df;
	}

}
