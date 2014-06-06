package uol.cubus.users;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Test;

import uol.cubus.JacksonObjectMapperProvider;
import uol.cubus.MediaType;
import uol.cubus.ResourceIntegrationTest;
import uol.cubus.filter.VersionFilter;
import uol.cubus.shared.LogHolder;

public class UserEndpointIntegrationTest {
	// extends JerseyTest {

	// @Override
	// protected Application configure() {
	// return new ResourceConfig(UserEndpoint.class);
	// }

	@Test
	public void shouldGetUserWithoutVersion() {

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

		assertThat(((Map<?, ?>) resource.getItem()).get("id"), is(1));
		assertThat(((Map<?, ?>) resource.getItem()).get("login"), is(notNullValue()));
	}
	
	@Test
	public void shouldGetUserWithVersion1AndWithoutLogin() {

		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				// .target(this.getBaseUri())
				.target("http://localhost:8080")
				.path("/rs/users/1")
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.header(VersionFilter.NAME, 1)
				.get();

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(response.getHeaderString("Content-Type"), is(MediaType.APPLICATION_RESOURCE_JSON));

		ResourceIntegrationTest<?> resource = response.readEntity(ResourceIntegrationTest.class);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resource.toString());

		assertThat(((Map<?, ?>) resource.getItem()).get("id"), is(1));
		assertThat(((Map<?, ?>) resource.getItem()).get("login"), is(nullValue()));
	}
	
	@Test
	public void shouldGetUserWithVersion2AndWithLogin() {

		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				// .target(this.getBaseUri())
				.target("http://localhost:8080")
				.path("/rs/users/1")
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.header(VersionFilter.NAME, 2)
				.get();

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(response.getHeaderString("Content-Type"), is(MediaType.APPLICATION_RESOURCE_JSON));

		ResourceIntegrationTest<?> resource = response.readEntity(ResourceIntegrationTest.class);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resource.toString());

		assertThat(((Map<?, ?>) resource.getItem()).get("id"), is(1));
		assertThat(((Map<?, ?>) resource.getItem()).get("login"), is(notNullValue()));
	}
}
