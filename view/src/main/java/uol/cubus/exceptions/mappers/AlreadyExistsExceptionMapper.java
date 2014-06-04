package uol.cubus.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import uol.cubus.ErrorDTO;
import uol.cubus.MediaType;
import uol.cubus.ResourceDTO;
import uol.cubus.exceptions.AlreadyExistsException;

@Provider
public class AlreadyExistsExceptionMapper implements ExceptionMapper<AlreadyExistsException> {

	@Override
	public Response toResponse(AlreadyExistsException e) {
		return Response
				.status(HttpServletResponse.SC_CONFLICT)
				.entity(ResourceDTO.getInstanceFrom(ErrorDTO.fromException(e)))
				.type(MediaType.APPLICATION_RESOURCE_JSON)
				.build();
	}

}
