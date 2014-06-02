package uol.cubus.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import uol.cubus.ResourceDTO;
import uol.cubus.exceptions.ErrorDTO;

@Provider
public class InternalExceptionMapper implements ExceptionMapper<Error> {

	@Override
	public Response toResponse(Error e) {
		return Response
				.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
				.entity(ResourceDTO.fromDTO(ErrorDTO.fromException(e)))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
