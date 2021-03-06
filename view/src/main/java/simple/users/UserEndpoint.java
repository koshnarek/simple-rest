package simple.users;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import simple.MediaType;
import simple.base.Page;
import simple.base.PageDTO;
import simple.base.PageError;
import simple.exceptions.AlreadyExistsException;
import simple.exceptions.BadParameterException;
import simple.exceptions.EmptyCollectionException;
import simple.exceptions.NotFoundException;
import simple.exceptions.NottingChangeException;

@Path("/")
public class UserEndpoint {

	@GET
	@Path(UserURI.USER)
	@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	public UserDTO getUser(@PathParam(UserURI.USER_ID) Long userId) throws NotFoundException {
		User user = User.find(userId);
		UserDTO userDTO = UserDTO.getNewInstanceFromEntity(user);
		return userDTO;
	}

	@GET
	@Path(UserURI.USERS)
	@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	public PageDTO<UserDTO> getUsers(@QueryParam(Page.PARAM) Integer pageIndex)
			throws EmptyCollectionException,
			BadParameterException {
		if (pageIndex == null) {
			throw new BadParameterException(PageError.PAGE_PARAMETER_NULL);
		}
		Page<User> page = User.findAll(pageIndex);
		PageDTO<UserDTO> pageDTO = new PageDTO<UserDTO>(pageIndex, page.getTotalPages(), UserDTO.getNewInstanceFromEntitys(page
				.getPageCollection()));
		return pageDTO;
	}

	@POST
	@Path(UserURI.USERS)
	@Consumes({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	public Response createUser(UserDTO userDTO) throws AlreadyExistsException {
		User user = User.getNewInstanceFromDTO(userDTO).save();
		userDTO = UserDTO.getNewInstanceFromEntity(user);
		return Response.status(HttpServletResponse.SC_CREATED).entity(userDTO).build();
	}

	@PUT
	@Path(UserURI.USER)
	@Consumes({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	public Response updateUser(UserDTO userDTO) throws NotFoundException, NottingChangeException {
		User user = User.getNewInstanceFromDTO(userDTO).update();
		userDTO = UserDTO.getNewInstanceFromEntity(user);
		return Response.status(HttpServletResponse.SC_ACCEPTED).entity(userDTO).build();
	}

	@DELETE
	@Path(UserURI.USER)
	@Produces({ MediaType.APPLICATION_RESOURCE_JSON, MediaType.APPLICATION_JSON })
	public Response deleteUser(@PathParam(UserURI.USER_ID) Long userId) throws NotFoundException {
		new User()
				.withId(userId)
				.delete();
		return Response.status(HttpServletResponse.SC_GONE).build();
	}
}
