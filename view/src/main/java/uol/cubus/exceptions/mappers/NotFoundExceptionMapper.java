package uol.cubus.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import uol.cubus.ResourceDTO;
import uol.cubus.exceptions.ErrorDTO;
import uol.cubus.exceptions.NotFoundException;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException e) {
		return Response
				.status(HttpServletResponse.SC_NOT_FOUND)
				.entity(ResourceDTO.fromDTO(ErrorDTO.fromException(e)))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
