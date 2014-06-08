package simple.users;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import simple.exceptions.DTOConstructionException;
import simple.shared.LogHolder;

public class UserDTOTest {

	Long id = 1L;
	String login = "teste@teste.com";
	Character status = 'A';

	@Test
	public void shouldBuildUserDTOFromUserWithAllFields() {
		User user = User.getNewInstance()
				.withId(id)
				.withLogin(login)
				.withStatus(status);

		UserDTO userDTO = UserDTO.getNewInstanceFromEntity(user);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), user.toString(), userDTO.toString());

		assertThat(userDTO.getId(), is(id));
		assertThat(userDTO.getLogin(), is(login));
		assertThat(userDTO.getStatus(), is(status));
	}

	@Test
	public void shouldBuildUserDTOFromUserWithNullStatus() {
		User user = User.getNewInstance()
				.withId(id)
				.withLogin(login)
				.withStatus(null);

		UserDTO userDTO = UserDTO.getNewInstanceFromEntity(user);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), user.toString(), userDTO.toString());

		assertThat(userDTO.getId(), is(id));
		assertThat(userDTO.getLogin(), is(login));
		assertThat(userDTO.getStatus(), is(nullValue()));
	}

	@Test
	public void shouldBuildUserDTOFromUserWithNullLogin() {
		User user = User.getNewInstance()
				.withId(id)
				.withLogin(null)
				.withStatus(status);

		UserDTO userDTO = UserDTO.getNewInstanceFromEntity(user);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), user.toString(), userDTO.toString());

		assertThat(userDTO.getId(), is(id));
		assertThat(userDTO.getLogin(), is(nullValue()));
		assertThat(userDTO.getStatus(), is(status));
	}

	@Test
	public void shouldBuildUserDTOFromUserWithNullId() {
		User user = User.getNewInstance()
				.withId(null)
				.withLogin(login)
				.withStatus(status);

		UserDTO userDTO = UserDTO.getNewInstanceFromEntity(user);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), user.toString(), userDTO.toString());

		assertThat(userDTO.getId(), is(nullValue()));
		assertThat(userDTO.getLogin(), is(login));
		assertThat(userDTO.getStatus(), is(status));
	}

	@Test
	public void shouldBuildUserDTOFromUserWithoutAllFields() {
		User user = new User();
		UserDTO userDTO = UserDTO.getNewInstanceFromEntity(user);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), user, userDTO.toString());
		
		assertThat(userDTO.getId(), is(nullValue()));
		assertThat(userDTO.getLogin(), is(nullValue()));
		assertThat(userDTO.getStatus(), is(nullValue()));
	}

	@Test
	public void shouldBuildCollectionOfUserDTOFromCollectionOfUserWithAllFields() {
		Collection<User> users = new ArrayList<User>();
		users.add(User.getNewInstance()
				.withId(id)
				.withLogin(login)
				.withStatus(status));
		Collection<UserDTO> userDTOs = UserDTO.getNewInstanceFromEntitys(users);
		
		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), users, userDTOs);
		
		UserDTO userDTO = userDTOs.stream().findFirst().get();
		assertThat(userDTO.getId(), is(id));
		assertThat(userDTO.getLogin(), is(login));
		assertThat(userDTO.getStatus(), is(status));
	}
	
	@Test(expected = DTOConstructionException.class)
	public void shouldThrowExceptionFromNullUser() {
		UserDTO.getNewInstanceFromEntity(null);
	}
}
