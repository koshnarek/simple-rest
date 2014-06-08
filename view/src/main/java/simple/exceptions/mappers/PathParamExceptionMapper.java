package simple.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ParamException.PathParamException;

import simple.MediaType;
import simple.base.ErrorDTO;

@Provider
public class PathParamExceptionMapper implements ExceptionMapper<PathParamException> {

	@Override
	public Response toResponse(PathParamException e) {
		return Response
				.status(HttpServletResponse.SC_NOT_FOUND)
				.entity(ErrorDTO.fromException(e))
				.type(MediaType.APPLICATION_RESOURCE_JSON)
				.build();
	}
}