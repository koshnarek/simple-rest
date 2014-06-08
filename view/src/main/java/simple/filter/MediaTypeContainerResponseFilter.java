package simple.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import simple.base.PageDTO;
import simple.base.ResourceDTO;

@Provider
public class MediaTypeContainerResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		final Object responseEntity = responseContext.getEntity();
		if (responseEntity instanceof ResourceDTO) {
			// already on mediaType
		} else if (responseEntity instanceof PageDTO) {
			PageDTO<?> pageDTO = (PageDTO<?>) responseContext.getEntity();
			responseContext.setEntity(new ResourceDTO<Object>(pageDTO, pageDTO.getPageIndex()));
		} else {
			responseContext.setEntity(new ResourceDTO<Object>(responseContext.getEntity()));
		}
	}

}
