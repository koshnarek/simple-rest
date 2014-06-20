package simple;

import java.text.DateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import simple.filter.VersionFilter;
import simple.filter.VersionSerializerFilter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;

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
				.setFilters(new SimpleFilterProvider().addFilter(VersionFilter.NAME, new VersionSerializerFilter()));
		result.registerModule(new JodaModule());
		DateFormat df = new ISO8601DateFormat();
		df.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Brazil/East")));
		result.setDateFormat(df);
		return result;
	}
}