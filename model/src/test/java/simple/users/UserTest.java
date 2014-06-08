package simple.users;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import simple.exceptions.ModelConstructionException;
import simple.shared.LogHolder;

public class UserTest {

	Long id = 1L;
	String login = "teste@teste.com";
	Character status = 'A';
	
	@Test
	public void shouldBuildUserFromUserDTOWithAllFields() {
		UserDTO userDTO = UserDTO.getNewInstance()
				.withId(id)
				.withLogin(login)
				.withStatus(status);

		User user = User.getNewInstanceFromDTO(userDTO);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), userDTO.toString(), user.toString());

		assertThat(user.getId(), is(id));
		assertThat(user.getLogin(), is(login));
		assertThat(user.getStatus(), is(status));
	}
	
	@Test
	public void shouldBuildUserFromUserDTOWithNullStatus() {
		UserDTO userDTO = UserDTO.getNewInstance()
				.withId(id)
				.withLogin(login)
				.withStatus(null);

		User user = User.getNewInstanceFromDTO(userDTO);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), userDTO.toString(), user.toString());
		
		assertThat(user.getId(), is(id));
		assertThat(user.getLogin(), is(login));
		assertThat(user.getStatus(), is(nullValue()));
	}

	@Test
	public void shouldBuildUserFromUserDTOWithNullLogin() {
		UserDTO userDTO = UserDTO.getNewInstance()
				.withId(id)
				.withLogin(null)
				.withStatus(status);

		User user = User.getNewInstanceFromDTO(userDTO);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), userDTO.toString(), user.toString());

		assertThat(user.getId(), is(id));
		assertThat(user.getLogin(), is(nullValue()));
		assertThat(user.getStatus(), is(status));
	}

	@Test
	public void shouldBuildUserFromUserDTOWithNullId() {
		UserDTO userDTO = UserDTO.getNewInstance()
				.withId(null)
				.withLogin(login)
				.withStatus(status);

		User user = User.getNewInstanceFromDTO(userDTO);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), userDTO.toString(), user.toString());

		assertThat(user.getId(), is(nullValue()));
		assertThat(user.getLogin(), is(login));
		assertThat(user.getStatus(), is(status));
	}

	@Test
	public void shouldBuildUserFromUserDTOWithoutAllFields() {
		UserDTO userDTO = new UserDTO();
		User user = User.getNewInstanceFromDTO(userDTO);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), user, userDTO.toString());
		
		assertThat(user.getId(), is(nullValue()));
		assertThat(user.getLogin(), is(nullValue()));
		assertThat(user.getStatus(), is(nullValue()));
	}

	@Test
	public void shouldBuildCollectionOfUserFromCollectionOfUserDTOWithAllFields() {
		Collection<UserDTO> userDTOs = new ArrayList<UserDTO>();
		userDTOs.add(UserDTO.getNewInstance()
				.withId(id)
				.withLogin(login)
				.withStatus(status));
		Collection<User> users = User.getNewInstanceFromDTOs(userDTOs);
		
		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), userDTOs, users);
		
		User user = users.stream().findFirst().get();
		assertThat(user.getId(), is(id));
		assertThat(user.getLogin(), is(login));
		assertThat(user.getStatus(), is(status));
	}
	
	@Test(expected = ModelConstructionException.class)
	public void shouldThrowExceptionFromNullUserDTO() {
		User.getNewInstanceFromDTO(null);
	}
}
