package simple.base;

public enum PageError implements Error<Integer> {

	PAGE_PARAMETER_NULL("page parameter is null");

	private final String message;

	private Integer pageIndex;

	PageError(String message) {
		this.message = message;
	}

	@Override
	public String getCode() {
		return this.getCode(this.ordinal());
	}

	@Override
	public Integer getId() {
		return this.pageIndex;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public Error<?> withId(Integer pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}

}
