package simple.users;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;

import simple.base.Page;
import simple.base.PageDTO;
import simple.exceptions.BadParameterException;
import simple.exceptions.EmptyCollectionException;
import simple.exceptions.NotFoundException;
import simple.shared.LogHolder;

public class UserEndpointTest {

	@Test
	public void shouldGetUser() throws NotFoundException {
		Long userId = 1L;
		UserEndpoint userEndpoint = new UserEndpoint();

		new MockUp<User>() {
			@Mock
			public User find(Long userId) throws NotFoundException {
				return User.getNewInstance().withId(userId);
			}
		};

		UserDTO userDTO = userEndpoint.getUser(userId);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), userDTO);

		assertThat(userDTO, is(UserDTO.getNewInstanceFromEntity(User.getNewInstance().withId(userId))));
	}

	@Test
	public void shouldGetUsers() throws EmptyCollectionException, BadParameterException {
		Long userId = 1L;
		Integer pageIndex = 1;
		UserEndpoint userEndpoint = new UserEndpoint();

		new MockUp<User>() {
			@Mock
			public Page<User> findAll(Integer page) throws EmptyCollectionException {
				return new Page<User>(Arrays.asList(User.getNewInstance().withId(userId)), pageIndex);
			}
		};

		PageDTO<UserDTO> pageDTO = userEndpoint.getUsers(pageIndex);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), pageDTO.toString());

		assertThat(pageDTO.getPageCollection(), contains(UserDTO.getNewInstanceFromEntity(User.getNewInstance().withId(userId))));
	}

	@Test(expected = NotFoundException.class)
	public void shouldThrowsNotFoundExceptionOnGetUser() throws NotFoundException {
		Long userId = 1L;
		UserEndpoint userEndpoint = new UserEndpoint();

		new MockUp<User>() {
			@Mock
			public User find(Long userId) throws NotFoundException {
				throw new NotFoundException(UserError.NOT_FOUND.withId(userId));
			}
		};

		userEndpoint.getUser(userId);
	}

}
