package simple.users;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;

import simple.base.ResourceDTO;
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

		ResourceDTO<UserDTO> resourceDTO = userEndpoint.getUser(userId);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(UserDTO.getNewInstanceFromEntity(User.getNewInstance().withId(userId))));
	}

	@Test
	public void shouldGetUsers() throws EmptyCollectionException, BadParameterException {
		Long userId = 1L;
		Integer page = 1;
		UserEndpoint userEndpoint = new UserEndpoint();

		new MockUp<User>() {
			@Mock
			public Collection<User> findAll(Integer page) {
				return Arrays.asList(User.getNewInstance().withId(userId));
			}
		};

		ResourceDTO<Collection<UserDTO>> resourceDTO = userEndpoint.getUsers(page);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItems(), contains(UserDTO.getNewInstanceFromEntity(User.getNewInstance().withId(userId))));
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
