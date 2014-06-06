package simple.exceptions;

import simple.Error;

public class AlreadyExistsException extends AbstractException {

	public AlreadyExistsException(Error<?> error) {
		super(error);
	}
}
