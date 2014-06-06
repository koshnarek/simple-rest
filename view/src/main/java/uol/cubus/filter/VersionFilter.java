package uol.cubus.filter;

import java.lang.reflect.Field;

import uol.cubus.annotations.JsonVersion;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

public class VersionFilter implements PropertyFilter {

	final public static String NAME = "X-version";

	public static final ThreadLocal<Integer> allowedVersion = new ThreadLocal<Integer>();

	@Override
	public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer) throws Exception {
		if (this.isToSerializeAccordingToAllowedVersion(pojo, writer)) {
			writer.serializeAsField(pojo, jgen, prov);
		}
	}

	private boolean isToSerializeAccordingToAllowedVersion(Object pojo, PropertyWriter writer) {
		for (Field field : pojo.getClass().getDeclaredFields()) {
			if ((field.getName().equals(writer.getName())) && (field.isAnnotationPresent(JsonVersion.class))
					&& (allowedVersion.get() != null)
					&& (field.getAnnotationsByType(JsonVersion.class)[0].version() > allowedVersion.get())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void serializeAsElement(Object elementValue, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer)
			throws Exception {
		writer.serializeAsElement(elementValue, jgen, prov);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void depositSchemaProperty(PropertyWriter writer, ObjectNode propertiesNode, SerializerProvider provider)
			throws JsonMappingException {
		writer.depositSchemaProperty(propertiesNode, provider);
	}

	@Override
	public void depositSchemaProperty(PropertyWriter writer, JsonObjectFormatVisitor objectVisitor, SerializerProvider provider)
			throws JsonMappingException {
		writer.depositSchemaProperty(objectVisitor);
	}

}
