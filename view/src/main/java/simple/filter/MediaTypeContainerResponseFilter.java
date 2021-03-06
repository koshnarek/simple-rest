package simple.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

//import org.jsondoc.core.pojo.JSONDoc;


import simple.base.PageDTO;
import simple.base.ResourceDTO;
import simple.schemas.SchemaURI;

@Provider
public class MediaTypeContainerResponseFilter implements ContainerResponseFilter {
	
	private static final String WADL_URI = "application.wadl";

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		final Object responseEntity = responseContext.getEntity();
		if (responseEntity instanceof ResourceDTO) {
			// already on mediaType
		} else if (responseEntity instanceof PageDTO) {
			PageDTO<?> pageDTO = (PageDTO<?>) responseContext.getEntity();
			responseContext.setEntity(new ResourceDTO<Object>(pageDTO, pageDTO.getPageIndex()));
		} else if(requestContext.getUriInfo().getAbsolutePath().getPath().contains(WADL_URI)) {
			// notting to do
//		} else if(responseEntity instanceof JSONDoc){
//			// documentation pojo, doesn't need mediaType enforcement...
		} else if(requestContext.getUriInfo().getAbsolutePath().getPath().contains(SchemaURI.SCHEMAS)) {
			// schema doesn't need mediaType enforcement...
		} else {
			responseContext.setEntity(new ResourceDTO<Object>(responseContext.getEntity()));
		}
	}

}
