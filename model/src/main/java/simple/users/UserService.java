package simple.users;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;

import simple.ApplicationConfig;
import simple.base.Page;
import simple.exceptions.AlreadyExistsException;
import simple.exceptions.EmptyCollectionException;
import simple.exceptions.NotFoundException;
import simple.exceptions.NottingChangeException;
import simple.users.repository.StatusRepository;
import simple.users.repository.UserRepository;

@Named
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

	public Page<User> findAll(Integer page) throws EmptyCollectionException {
		org.springframework.data.domain.Page<User> pageReturn = userRepository.findAll(new PageRequest(--page, Page.SIZE));
		Collection<User> users = pageReturn.getContent();
		Integer totalPages = pageReturn.getTotalPages();
		if (users == null || users.isEmpty()) {
			throw new EmptyCollectionException(UserError.EMPTY_COLLECTION);
		} else {
			return new Page<User>(users, totalPages);
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

	public User update(User user) throws NottingChangeException, NotFoundException {
		if (user != null && user.getId() != null) {
			User userOld = this.find(user.getId());
			if (user.equals(userOld)) {
				throw new NottingChangeException(UserError.NOTTING_CHANGE.withId(user.getId()));
			} else {
				statusRepository.saveAndFlush(user.getStatusEntity());
				user = userRepository.saveAndFlush(user);
			}
		} else {
			throw new NottingChangeException(UserError.NOTTING_CHANGE.withId(user.getId()));
		}
		return user;
	}

	public void delete(@NotNull Long id) throws NotFoundException {
		if (id == null) {
			throw new NotFoundException(UserError.NOT_FOUND.withId(null));
		} else {
			userRepository.delete(id);
		}
	}
}
