package simple.exceptions;

import simple.base.Error;

public class NotFoundException extends AbstractException {

	public NotFoundException(Error<?> error) {
		super(error);
	}
}
