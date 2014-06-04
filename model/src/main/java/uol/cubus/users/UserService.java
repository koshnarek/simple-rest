package uol.cubus.users;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

import uol.cubus.exceptions.AlreadyExistsException;
import uol.cubus.exceptions.NotFoundException;
import uol.cubus.users.repository.UserRepository;

@Configurable
public class UserService {

	private static UserService instance;

	@Inject
	private UserRepository userRepository;

	private UserService() {
		// just to prevent new;
	}

	public static UserService getInstance() {
		return instance == null ? new UserService() : instance;
	}

	public User find(@NotNull Long id) throws NotFoundException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new NotFoundException(UserError.NOT_FOUND.withId(id));
		}
		return user;
	}

	public User create(@NotNull User user) throws AlreadyExistsException {
		if (user != null) {
			try {
				user = userRepository.saveAndFlush(user);
			} catch (EntityExistsException e) {
				throw new AlreadyExistsException(UserError.ALREADY_EXISTS.withId(user.getId()));
			}
		}
		return user;
	}

}
