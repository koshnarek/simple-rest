package simple;

import simple.exceptions.NotFoundException;

public interface Deletable {

	public void delete() throws NotFoundException;
}
