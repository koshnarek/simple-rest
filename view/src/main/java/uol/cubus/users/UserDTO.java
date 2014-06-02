package uol.cubus.users;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import uol.cubus.AbstractDTO;

@XmlRootElement
public class UserDTO extends AbstractDTO<User> {

	@XmlElement
	private Long id;
	@XmlElement
	private String login;
	@XmlElement
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

	public static UserDTO getFrom(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.populateFrom(user);
		userDTO.setStatus((user != null && user.getStatus() != null) ? user.getStatus().getCode() : null);
		userDTO.setLink(StringUtils.replace(UserURI.USER, "{" + UserURI.USER_ID + "}", String.valueOf(user.getId())));
		return userDTO;
	}
}
