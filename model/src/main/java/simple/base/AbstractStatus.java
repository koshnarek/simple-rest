package simple.base;


public abstract class AbstractStatus<T, K> extends AbstractDomain<K> {

	transient protected T type;

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}
}
