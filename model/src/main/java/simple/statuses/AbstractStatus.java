package simple.statuses;

import simple.AbstractDomain;

public abstract class AbstractStatus<T, K> extends AbstractDomain<K> {

	transient protected T type;

	abstract public Character getCode();

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}
}
