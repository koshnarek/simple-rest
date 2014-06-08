package simple.exceptions;

import simple.AbstractDTO;

public class DTOConstructionException extends InternalException {
	
	private static final String MESSAGE = "Unable to construct %s from %s";

	public <T> DTOConstructionException(AbstractDTO<T> dto, T domain,
			Exception e) {
		super(String.format(MESSAGE, dto, domain), e);
	}

}
