package simple.users;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import simple.MediaType;
import simple.Page;
import simple.ResourceDTO;
import simple.exceptions.AlreadyExistsException;
import simple.exceptions.BadParameterException;
import simple.exceptions.EmptyCollectionException;
import simple.exceptions.NotFoundException;

@Path("/")
public class UserEndpoint {

	@GET
	@Path(UserURI.USER)
	@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	public ResourceDTO<UserDTO> getUser(@PathParam(UserURI.USER_ID) Long userId) throws NotFoundException {
		User user = User.find(userId);
		UserDTO userDTO = UserDTO.getNewInstanceFromEntity(user);
		return new ResourceDTO<UserDTO>(userDTO);
	}
	
	@GET
	@Path(UserURI.USERS)
	@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	public ResourceDTO<Collection<UserDTO>> getUsers(@QueryParam(Page.PARAM) Integer page) throws EmptyCollectionException, BadParameterException {
		if(page == null) {
			throw new BadParameterException(UserError.PAGE_PARAMETER_NULL);
		}
		page--;
		Collection<User> users = User.findAll(page);
		Collection<UserDTO> userDTOs = UserDTO.getNewInstanceFromEntitys(users);
		return new ResourceDTO<Collection<UserDTO>>(userDTOs, page);
	}

	@POST
	@Path(UserURI.USERS)
	@Consumes({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	public ResourceDTO<UserDTO> createUser(UserDTO userDTO) throws AlreadyExistsException {
		User user = User.getNewInstanceFromDTO(userDTO).save();
		userDTO = UserDTO.getNewInstanceFromEntity(user);
		return new ResourceDTO<UserDTO>(userDTO);
	}
}
