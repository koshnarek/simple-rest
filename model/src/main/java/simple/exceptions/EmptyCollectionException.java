package simple.exceptions;

import simple.base.Error;

public class EmptyCollectionException extends AbstractException {

	public EmptyCollectionException(Error<?> error) {
		super(error);
	}
}
