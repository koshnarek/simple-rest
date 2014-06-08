package simple.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import simple.MediaType;
import simple.base.ErrorDTO;
import simple.exceptions.BadParameterException;

@Provider
public class BadParameterExceptionMapper implements ExceptionMapper<BadParameterException> {

	@Override
	public Response toResponse(BadParameterException e) {
		return Response
				.status(HttpServletResponse.SC_BAD_REQUEST)
				.entity(ErrorDTO.fromException(e))
				.type(MediaType.APPLICATION_RESOURCE_JSON)
				.build();
	}

}
