package simple.filter;

import java.lang.reflect.Field;

import simple.annotations.JsonVersion;
import simple.shared.LogHolder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

public class VersionSerializerFilter implements PropertyFilter {

	@Override
	public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer) throws Exception {
		if (this.isToSerializeAccordingToAllowedVersion(pojo, writer)) {
			writer.serializeAsField(pojo, jgen, prov);
		}
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

	private boolean isVersionAnnotationPresent(Field field, PropertyWriter writer) {
		return field.getName().equals(writer.getName()) && field.isAnnotationPresent(JsonVersion.class);
	}

	private boolean isNotToInclude(Field field) {
		return field.getAnnotationsByType(JsonVersion.class)[0].action().equals(JsonVersion.Action.INCLUDE)
				&& (field.getAnnotationsByType(JsonVersion.class)[0].version() > VersionAllowedHolder.getInstance().getVersion());
	}

	private boolean isToExclude(Field field) {
		return field.getAnnotationsByType(JsonVersion.class)[0].action().equals(JsonVersion.Action.EXCLUDE)
				&& (field.getAnnotationsByType(JsonVersion.class)[0].version() <= VersionAllowedHolder.getInstance().getVersion());
	}

	private boolean isToSerializeAccordingToAllowedVersion(Object pojo, PropertyWriter writer) {
		for (Field field : pojo.getClass().getDeclaredFields()) {
			if (this.isVersionAnnotationPresent(field, writer) && VersionAllowedHolder.getInstance().getVersion() != null) {
				if ((this.isNotToInclude(field)) || (this.isToExclude(field))) {
					LogHolder.getLogger().debug("operation=JsonVersion, field={}, serializable={}", field.getName(), Boolean.FALSE);
					return false;
				}
				break;
			}
		}
		LogHolder.getLogger().debug("operation=JsonVersion, field={}, serializable={}", writer.getName(), Boolean.TRUE);
		return true;
	}
}
