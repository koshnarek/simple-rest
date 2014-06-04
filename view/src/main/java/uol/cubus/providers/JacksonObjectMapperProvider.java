package uol.cubus.providers;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper;
//	final ObjectMapper combinedObjectMapper;

	public JacksonObjectMapperProvider() {
		defaultObjectMapper = createDefaultMapper();
//		combinedObjectMapper = createCombinedObjectMapper();
	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {
		return defaultObjectMapper;
	}

//	private static ObjectMapper createCombinedObjectMapper() {
//		return new ObjectMapper()
//				.configure(SerializationFeature.WRAP_ROOT_VALUE, true)
//				.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
//				.setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
//	}

	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.enable(SerializationFeature.INDENT_OUTPUT);
		result.setSerializationInclusion(Include.NON_NULL);
		return result;
	}

//	private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {
//		final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
//		final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
//		return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
//	}
}