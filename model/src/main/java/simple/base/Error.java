package simple.base;

public interface Error<T> {

	public static final String NOT_FOUND_MSG = "%entity %s not found";
	public static final String ALREADY_EXISTS_MSG = "%entity %s already exists";
	public static final String NOTTING_CHANGE_MSG = "%entity %s has not been modified";
	public static final String EMPTY_COLLECTION_MSG = "empty %entity on page";

	public String getCode();

	public default String getCode(int ordinal) {
		return this.getClass().getSimpleName().toUpperCase() + "-" + String.valueOf(ordinal);
	}

	public static String messageForEntity(Class<?> clazz, String msg) {
		if (msg != null && clazz != null) {
			return msg.replace("%entity", clazz.getSimpleName());
		}
		else {
			return msg;
		}
	}

	public String getMessage();

	public T getId();

	public Error<?> withId(T id);

}
