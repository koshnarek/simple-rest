package uol.cubus.users;

import uol.cubus.Error;

public enum UserError implements Error<Long> {

	NOT_FOUND(404001, "user %s not found");

	private Integer code;

	private String message;

	private Long userId;

	UserError(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public Long getId() {
		return this.userId;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public Error<?> withId(Long userId) {
		this.userId = userId;
		return this;
	}
}
