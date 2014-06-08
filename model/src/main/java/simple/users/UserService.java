package simple.users;

import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;

import simple.ApplicationConfig;
import simple.Page;
import simple.exceptions.AlreadyExistsException;
import simple.exceptions.EmptyCollectionException;
import simple.exceptions.NotFoundException;
import simple.users.repository.StatusRepository;
import simple.users.repository.UserRepository;

public class UserService {

	private static UserService instance;

	private UserRepository userRepository = ApplicationConfig.getInjectorHolder().getInstance(UserRepository.class);

	private StatusRepository statusRepository = ApplicationConfig.getInjectorHolder().getInstance(StatusRepository.class);

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

	public Collection<User> findAll(Integer page) throws EmptyCollectionException {
		Collection<User> users = userRepository.findAll(new PageRequest(page, Page.SIZE)).getContent();
		if (users == null || users.isEmpty()) {
			throw new EmptyCollectionException(UserError.EMPTY_COLLECTION);
		} else {
			return users;
		}
	}

	public User create(@NotNull User user) throws AlreadyExistsException {
		if (user != null) {
			try {
				statusRepository.saveAndFlush(user.getStatusEntity());
				user = userRepository.saveAndFlush(user);
			} catch (EntityExistsException e) {
				throw new AlreadyExistsException(UserError.ALREADY_EXISTS.withId(user.getId()));
			}
		}
		return user;
	}

}
