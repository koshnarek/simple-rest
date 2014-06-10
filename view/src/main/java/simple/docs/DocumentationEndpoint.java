package simple.docs;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

//import org.jsondoc.core.pojo.JSONDoc;
//import org.jsondoc.core.util.JSONDocUtils;

@Path("/")
public class DocumentationEndpoint {

//	@GET
//	@Path(DocumentationURI.DOCS)
//	public JSONDoc getDoc(@QueryParam("version") String version, @QueryParam("path") String path) {
//		try{
//			//fucked it up! JSONDocUtils depends on org.reflections:reflections:0.9.9-RC1 and this version brakes guice! 
//			return JSONDocUtils.getApiDoc(version, path, Arrays.asList("simple.users"));			
//		} catch(Throwable t) {
//			t.printStackTrace();
//			throw t;
//		}
//	}

}
