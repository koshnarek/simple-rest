package simple.base;

import simple.exceptions.AlreadyExistsException;

public interface Salvable<T> {
	public T save() throws AlreadyExistsException;
}
