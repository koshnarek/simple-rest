package simple.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import simple.ErrorDTO;
import simple.MediaType;
import simple.ResourceDTO;

@Provider
public class UnexpectedExceptionMapper implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException e) {
		return Response
				.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
				.entity(new ResourceDTO<ErrorDTO>(ErrorDTO.fromException(e)))
				.type(MediaType.APPLICATION_RESOURCE_JSON)
				.build();
	}
}