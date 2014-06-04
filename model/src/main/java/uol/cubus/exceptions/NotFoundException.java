package uol.cubus.exceptions;

import uol.cubus.Error;

public class NotFoundException extends AbstractException {

	public NotFoundException(Error<?> error) {
		super(error);
	}
}
