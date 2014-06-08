package simple.users;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import simple.AbstractDTO;
import simple.annotations.Domain;
import simple.annotations.JsonVersion;
import simple.annotations.JsonVersion.Action;
import simple.filter.VersionFilter;

import com.fasterxml.jackson.annotation.JsonFilter;

@Domain(name = "User")
@JsonFilter(value = VersionFilter.NAME)
public class UserDTO extends AbstractDTO<User> {

	private Long id;
	@JsonVersion(action = Action.INCLUDE, version = 2)
	private String login;
	@JsonVersion(action = Action.EXCLUDE, version = 3)
	private Character status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public UserDTO withId(Long id) {
		this.id = id;
		return this;
	}

	public UserDTO withLogin(String login) {
		this.login = login;
		return this;
	}

	public UserDTO withStatus(Character status) {
		this.status = status;
		return this;
	}

	public static UserDTO getNewInstance() {
		return new UserDTO();
	}

	public static Collection<UserDTO> getNewInstanceFromEntitys(Collection<User> users) {
		Collection<UserDTO> userDTOs = new ArrayList<UserDTO>(users.size());
		users.forEach(user -> userDTOs.add(UserDTO.getNewInstanceFromEntity(user)));
		return userDTOs;
	}

	public static UserDTO getNewInstanceFromEntity(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.populateFrom(user);
		//userDTO.setStatus((user != null && user.getStatus() != null) ? user.getStatus().getCode() : null);
		userDTO.setLink(StringUtils.replace(UserURI.USER, "{" + UserURI.USER_ID + "}", String.valueOf(user.getId())));
		return userDTO;
	}
}
