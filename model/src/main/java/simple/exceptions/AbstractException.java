package simple.exceptions;

import simple.base.Error;

public class AbstractException extends Exception {

	private String code;

	public AbstractException(Error<?> code) {
		super(String.format(code.getMessage(), code.getId()));
		this.code = code.getCode();
	}

	public String getCode() {
		return this.code;
	}
}
