package simple.base;

public interface Error<T> {

	public Integer getCode();
	
	public String getMessage();
	
	public T getId();
	
	public Error<?> withId(T id);
}
