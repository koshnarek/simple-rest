package uol.cubus.exceptions;

public class InternalException extends RuntimeException {
	
	public static Integer CODE = 50666;
	
	public InternalException(String message, Exception e) {
		super(message, e);
	}

}