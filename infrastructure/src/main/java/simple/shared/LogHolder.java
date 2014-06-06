package simple.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHolder {

	public static Logger getLogger() {
		return LoggerFactory.getLogger("CUBUS");
	}
}
