package simple.exceptions;

import simple.base.Error;

public class NottingChangeException extends AbstractException {

	public NottingChangeException(Error<?> error) {
		super(error);
	}
}
