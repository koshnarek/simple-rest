package simple.statuses;

import simple.AbstractDomain;

public abstract class AbstractStatus<T> extends AbstractDomain {

	protected T type;

	abstract public Character getCode();

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}

	public String getDescription() {
		return this.getType() != null ? this.getType().toString() : null;
	}

}
