package uol.cubus.users;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import uol.cubus.MediaType;
import uol.cubus.providers.JacksonObjectMapperProvider;
import uol.cubus.shared.LogHolder;

public class UserEndpointTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(UserEndpoint.class);
	}

//	@Test
	public void shouldGetValidUser() {

		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				.target(this.getBaseUri())
				.path("/users/1")
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.get();

		UserDTO userDTO = response.readEntity(UserDTO.class);
		LogHolder.getLogger().info(userDTO.getLogin());
		
		System.out.println(response.toString());

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(userDTO.getId(), is(1L));
	}
}
