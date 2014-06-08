package simple.exceptions;

import simple.base.Error;

public class AlreadyExistsException extends AbstractException {

	public AlreadyExistsException(Error<?> error) {
		super(error);
	}
}
