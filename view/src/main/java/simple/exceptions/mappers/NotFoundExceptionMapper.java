package simple.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import simple.ErrorDTO;
import simple.MediaType;
import simple.ResourceDTO;
import simple.exceptions.NotFoundException;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException e) {
		return Response
				.status(HttpServletResponse.SC_NOT_FOUND)
				.entity(ResourceDTO.getInstanceFrom(ErrorDTO.fromException(e)))
				.type(MediaType.APPLICATION_RESOURCE_JSON)
				.build();
	}

}
