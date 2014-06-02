package uol.cubus.users;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import uol.cubus.exceptions.DTOConstructionException;
import uol.cubus.shared.LogHolder;

public class UserBuilderTest {

	long id = 1L;
	String login = "teste@teste.com";
	char status = 'A';

	@Test
	public void shouldBuildUserDTOFromUserWithAllFields() {
		User user = User.getNewInstance()
				.withId(id)
				.withLogin(login)
				.withStatus(status);

		UserDTO userDTO = UserDTO.getFrom(user);

		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object(){}.getClass().getEnclosingMethod().getName(), user.toString(), userDTO.toString());

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

		UserDTO userDTO = UserDTO.getFrom(user);
		
		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object(){}.getClass().getEnclosingMethod().getName(), user.toString(), userDTO.toString());

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

		UserDTO userDTO = UserDTO.getFrom(user);
		
		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object(){}.getClass().getEnclosingMethod().getName(), user.toString(), userDTO.toString());

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

		UserDTO userDTO = UserDTO.getFrom(user);
		
		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object(){}.getClass().getEnclosingMethod().getName(), user.toString(), userDTO.toString());

		assertThat(userDTO.getId(), is(nullValue()));
		assertThat(userDTO.getLogin(), is(login));
		assertThat(userDTO.getStatus(), is(status));
	}

	@Test
	public void shouldBuildUserDTOFromUserWithoutAllFields() {
		User user = new User();
		UserDTO userDTO = UserDTO.getFrom(user);
		
		LogHolder.getLogger().info("{}\n\t{}\n\t{}", new Object(){}.getClass().getEnclosingMethod().getName(), user, userDTO.toString());
	}

	@Test(expected = DTOConstructionException.class)
	public void shouldThrowExceptionFromNullUser() {
		UserDTO.getFrom(null);
	}
}
