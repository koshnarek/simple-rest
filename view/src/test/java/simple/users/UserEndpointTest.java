package simple.users;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;

import simple.ResourceDTO;
import simple.exceptions.NotFoundException;
import simple.shared.LogHolder;
import simple.users.User;
import simple.users.UserDTO;
import simple.users.UserEndpoint;
import simple.users.UserError;

public class UserEndpointTest {

	@Test
	public void shouldGetUser() throws NotFoundException {
		Long userId = 1L;
		Integer version = null;
		UserEndpoint userEndpoint = new UserEndpoint();

		new MockUp<User>() {
			@Mock
			public User find(Long userId) throws NotFoundException {
				return User.getNewInstance().withId(userId);
			}
		};

		ResourceDTO resourceDTO = userEndpoint.getUser(version, userId);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(UserDTO.getInstanceFrom(User.getNewInstance().withId(userId))));
	}

	@Test(expected = NotFoundException.class)
	public void shouldThrowsNotFoundException() throws NotFoundException {
		Long userId = 1L;
		Integer version = null;
		UserEndpoint userEndpoint = new UserEndpoint();

		new MockUp<User>() {
			@Mock
			public User find(Long userId) throws NotFoundException {
				throw new NotFoundException(UserError.NOT_FOUND.withId(userId));
			}
		};

		userEndpoint.getUser(version, userId);
	}

}
