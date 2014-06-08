package simple.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import simple.ErrorDTO;
import simple.MediaType;
import simple.ResourceDTO;
import simple.exceptions.AlreadyExistsException;

@Provider
public class AlreadyExistsExceptionMapper implements ExceptionMapper<AlreadyExistsException> {

	@Override
	public Response toResponse(AlreadyExistsException e) {
		return Response
				.status(HttpServletResponse.SC_CONFLICT)
				.entity(new ResourceDTO<ErrorDTO>(ErrorDTO.fromException(e)))
				.type(MediaType.APPLICATION_RESOURCE_JSON)
				.build();
	}

}
