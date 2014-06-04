package uol.cubus.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import uol.cubus.MediaType;
import uol.cubus.ResourceDTO;
import uol.cubus.exceptions.AlreadyExistsException;
import uol.cubus.exceptions.ErrorDTO;

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
