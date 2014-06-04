package uol.cubus.exceptions;

import uol.cubus.Error;

public class AlreadyExistsException extends AbstractException {

	public AlreadyExistsException(Error<?> error) {
		super(error);
	}
}
