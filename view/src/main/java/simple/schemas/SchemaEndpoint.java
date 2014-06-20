package simple.schemas;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.UriInfo;

import simple.exceptions.InternalException;
import simple.filter.VersionFilter;
import simple.filter.VersionSerializerFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Path("/")
public class SchemaEndpoint {

	@Inject
	UriInfo uri;

	@SuppressWarnings("deprecation")
	@GET
	@Path(SchemaURI.SCHEMAS)
	public String getSchema(@QueryParam("class") String path) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setFilters(new SimpleFilterProvider().addFilter(VersionFilter.NAME, new VersionSerializerFilter()));
		try {
			return mapper.generateJsonSchema(Class.forName(path)).toString();
		} catch (Exception e) {
			throw new InternalException(e.getMessage(), e);
		}
	}

}
