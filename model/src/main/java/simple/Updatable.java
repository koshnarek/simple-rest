package simple;

import simple.exceptions.NotFoundException;
import simple.exceptions.NottingChangeException;

public interface Updatable<T> {

	public T update() throws NotFoundException, NottingChangeException;
}
