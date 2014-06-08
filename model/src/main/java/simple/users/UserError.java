package simple.users;

import simple.base.Error;

public enum UserError implements Error<Long> {

	NOT_FOUND(1, "user %s not found"),
	ALREADY_EXISTS(2, "user %s already exists"),
	NOTTING_CHANGE(3, "user %s has not been modified"),
	EMPTY_COLLECTION(4, "empty users on page"),
	PAGE_PARAMETER_NULL(5, "page parameter not informed");

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
