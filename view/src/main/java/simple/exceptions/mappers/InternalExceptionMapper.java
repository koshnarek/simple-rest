package simple.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import simple.MediaType;
import simple.base.ErrorDTO;

@Provider
public class InternalExceptionMapper implements ExceptionMapper<Error> {

	@Override
	public Response toResponse(Error e) {
		return Response
				.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
				.entity(ErrorDTO.fromException(e))
				.type(MediaType.APPLICATION_RESOURCE_JSON)
				.build();
	}
}
