package simple.users;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import simple.JacksonObjectMapperProvider;
import simple.MediaType;
import simple.ResourceIntegrationDTO;
import simple.filter.VersionFilter;
import simple.shared.DateFormatHolder;
import simple.shared.LogHolder;

public class UserEndpointIntegrationTest {

	final public static String TARGET = "http://localhost:8080";
	final private static String URI = "/rs/users";
	final private static String USER_ID = "/1";

	public static UserDTO userDTO;

	@BeforeClass
	public static void createUser() throws ParseException {
		userDTO = new UserDTO()
				.withLogin("teste@teste.com")
				.withStatus('A')
				.withSignup(DateFormatHolder.get().parse("2014-06-20T00:58:43Z"));
		ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				.target(TARGET)
				.path(URI)
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.post(Entity.entity(userDTO, MediaType.APPLICATION_RESOURCE_JSON));
	}

	@Test
	public void shouldGetUserWithoutVersionWithAllFields() {

		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				.target(TARGET)
				.path(URI + USER_ID)
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.get();

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(response.getHeaderString("Content-Type"), is(MediaType.APPLICATION_RESOURCE_JSON));

		ResourceIntegrationDTO<?> resource = response.readEntity(ResourceIntegrationDTO.class);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resource.toString());

		assertThat(((Map<?, ?>) resource.getItem()).get("id"), is(1));
		assertThat(((Map<?, ?>) resource.getItem()).get("login"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("status"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("signup"), is(notNullValue()));
	}

	@Test
	public void shouldGetUserWithVersion1AndWithoutLoginAndWithStatus() {

		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				.target(TARGET)
				.path(URI + USER_ID)
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.header(VersionFilter.NAME, 1)
				.get();

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(response.getHeaderString("Content-Type"), is(MediaType.APPLICATION_RESOURCE_JSON));

		ResourceIntegrationDTO<?> resource = response.readEntity(ResourceIntegrationDTO.class);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resource.toString());

		assertThat(((Map<?, ?>) resource.getItem()).get("id"), is(1));
		assertThat(((Map<?, ?>) resource.getItem()).get("login"), is(nullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("status"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("signup"), is(notNullValue()));
	}

	@Test
	public void shouldGetUserWithVersion2AndWithLoginAndStatus() {

		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				.target(TARGET)
				.path(URI + USER_ID)
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.header(VersionFilter.NAME, 2)
				.get();

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(response.getHeaderString("Content-Type"), is(MediaType.APPLICATION_RESOURCE_JSON));

		ResourceIntegrationDTO<?> resource = response.readEntity(ResourceIntegrationDTO.class);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resource.toString());

		assertThat(((Map<?, ?>) resource.getItem()).get("id"), is(1));
		assertThat(((Map<?, ?>) resource.getItem()).get("login"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("status"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("signup"), is(notNullValue()));
	}

	@Test
	public void shouldGetUserWithVersion3AndWithoutStatus() {

		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				.target(TARGET)
				.path(URI + USER_ID)
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.header(VersionFilter.NAME, 3)
				.get();

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(response.getHeaderString("Content-Type"), is(MediaType.APPLICATION_RESOURCE_JSON));

		ResourceIntegrationDTO<?> resource = response.readEntity(ResourceIntegrationDTO.class);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resource.toString());

		assertThat(((Map<?, ?>) resource.getItem()).get("id"), is(1));
		assertThat(((Map<?, ?>) resource.getItem()).get("login"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("status"), is(nullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("signup"), is(notNullValue()));
	}

	@Test
	public void shouldGetUserWithSpecificSignupDate() throws ParseException {
		Response response = ClientBuilder.newClient()
				.register(JacksonObjectMapperProvider.class)
				.target(TARGET)
				.path(URI + USER_ID)
				.request(MediaType.APPLICATION_RESOURCE_JSON)
				.get();

		assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
		assertThat(response.getHeaderString("Content-Type"), is(MediaType.APPLICATION_RESOURCE_JSON));

		ResourceIntegrationDTO<?> resource = response.readEntity(ResourceIntegrationDTO.class);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resource.toString());

		assertThat(((Map<?, ?>) resource.getItem()).get("id"), is(1));
		assertThat(((Map<?, ?>) resource.getItem()).get("login"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("status"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("signup"), is(notNullValue()));
		assertThat(((Map<?, ?>) resource.getItem()).get("signup"), is(DateFormatHolder.get().format(userDTO.getSignup())));
	}
}
