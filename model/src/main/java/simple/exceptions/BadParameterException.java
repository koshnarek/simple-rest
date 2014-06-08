package simple.exceptions;

import simple.base.Error;

public class BadParameterException extends AbstractException {

	public BadParameterException(Error<?> error) {
		super(error);
	}
}
