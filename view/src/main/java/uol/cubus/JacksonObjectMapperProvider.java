package uol.cubus;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import uol.cubus.filter.VersionFilter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Provider
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper;

	public JacksonObjectMapperProvider() {
		this.defaultObjectMapper = createDefaultMapper();
	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {
		return this.defaultObjectMapper;
	}

	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.enable(SerializationFeature.INDENT_OUTPUT)
				.setSerializationInclusion(Include.NON_NULL)
				.setFilters(new SimpleFilterProvider().addFilter(VersionFilter.NAME, new VersionFilter()));
		return result;
	}
}