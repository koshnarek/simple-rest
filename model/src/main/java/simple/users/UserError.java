package simple.users;

import simple.base.Error;

public enum UserError implements Error<Long> {

	NOT_FOUND(Error.NOT_FOUND_MSG),
	ALREADY_EXISTS(Error.ALREADY_EXISTS_MSG),
	NOTTING_CHANGE(Error.NOTTING_CHANGE_MSG),
	EMPTY_COLLECTION(Error.EMPTY_COLLECTION_MSG);

	private final String message;

	private Long userId;

	UserError(String message) {
		this.message = Error.messageForEntity(User.class, message);
	}

	@Override
	public String getCode() {
		return this.getCode(this.ordinal());
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
