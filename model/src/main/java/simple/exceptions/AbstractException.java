package simple.exceptions;

import simple.base.Error;

public class AbstractException extends Exception {

	private Integer code;

	public AbstractException(Error<?> code) {
		super(String.format(code.getMessage(), code.getId()));
		this.code = code.getCode();
	}

	public Integer getCode() {
		return this.code;
	}
}
