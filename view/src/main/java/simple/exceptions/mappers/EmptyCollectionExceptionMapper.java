package simple.exceptions.mappers;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import simple.MediaType;
import simple.base.ErrorDTO;
import simple.exceptions.EmptyCollectionException;

@Provider
public class EmptyCollectionExceptionMapper implements ExceptionMapper<EmptyCollectionException> {

	@Override
	public Response toResponse(EmptyCollectionException e) {
		return Response
				.status(HttpServletResponse.SC_NO_CONTENT)
				.entity(ErrorDTO.fromException(e))
				.type(MediaType.APPLICATION_RESOURCE_JSON)
				.build();
	}

}
