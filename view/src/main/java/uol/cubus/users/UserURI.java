package uol.cubus.users;

public interface UserURI {

	public static String USERS = "/users";
	public static String USER_ID = "userId";
	public static String USER = USERS + "/{" + USER_ID + "}";

}
