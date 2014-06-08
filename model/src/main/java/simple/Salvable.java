package simple;

import simple.exceptions.AlreadyExistsException;

public interface Salvable<T> {
	public T save() throws AlreadyExistsException;
}
