package simple.users;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import simple.MediaType;
import simple.ResourceDTO;
import simple.exceptions.NotFoundException;
import simple.filter.VersionFilter;
import simple.users.User;

@Path("/")
@Consumes({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
public class UserEndpoint {

	@GET
	@Path(UserURI.USER)
	public ResourceDTO getUser(@HeaderParam(VersionFilter.NAME) Integer version, @PathParam(UserURI.USER_ID) Long userId) throws NotFoundException {
		VersionFilter.allowedVersion.set(version);
		User user = User.find(userId);
		UserDTO userDTO = UserDTO.getInstanceFrom(user);
		return ResourceDTO.getInstanceFrom(userDTO);
		// return ResourceDTO.fromDTOs(Arrays.asList(userDTO), 1, 10);
	}
}
