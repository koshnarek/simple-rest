package simple.exceptions;

import simple.Error;

public class NotFoundException extends AbstractException {

	public NotFoundException(Error<?> error) {
		super(error);
	}
}
