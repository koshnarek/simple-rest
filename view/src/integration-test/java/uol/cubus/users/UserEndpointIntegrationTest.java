package uol.cubus.users;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Test;

import uol.cubus.MediaType;
import uol.cubus.ResourceIntegrationTest;
import uol.cubus.providers.JacksonObjectMapperProvider;
import uol.cubus.shared.LogHolder;

public class UserEndpointIntegrationTest {
	// extends JerseyTest {

	// @Override
	// protected Application configure() {
	// return new ResourceConfig(UserEndpoint.class);
	// }

	@Test
	public void shouldGetUser() {

		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				// .target(this.getBaseUri())
				.target("http://localhost:8080")
				.path("/rs/users/1")
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.get();

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(response.getHeaderString("Content-Type"), is(MediaType.APPLICATION_RESOURCE_JSON));

		ResourceIntegrationTest<?> resource = response.readEntity(ResourceIntegrationTest.class);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resource.toString());
		
		assertThat(((Map<?, ?>)resource.getItem()).get("id"), is(1));
	}
}
