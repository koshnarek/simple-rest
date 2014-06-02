package uol.cubus.users;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Configurable;

import uol.cubus.exceptions.NotFoundException;
import uol.cubus.users.repository.UserRepository;

@Configurable
@Singleton
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

	public User findUser(Long id) throws NotFoundException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new NotFoundException(UserError.NOT_FOUND.withId(id));
		}
		return user;
	}

	public User create(User user) {
		if (user != null) {
			user = userRepository.saveAndFlush(user);
		}
		return user;
	}

}
