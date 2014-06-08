package simple.exceptions;

import simple.base.AbstractDomain;

public class ModelConstructionException extends InternalException {
	
	private static final String MESSAGE = "Unable to construct %s from %s";

	public <T> ModelConstructionException(AbstractDomain<T> model, T dto,
			Exception e) {
		super(String.format(MESSAGE, model, dto), e);
	}

}
