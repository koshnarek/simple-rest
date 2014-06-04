package uol.cubus.users;

import org.apache.commons.lang3.StringUtils;

import uol.cubus.AbstractDTO;

//@XmlRootElement
//@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.NONE)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO extends AbstractDTO<User> {

//	@XmlElement
//	@JsonProperty("id")
	private Long id;
//	@XmlElement
//	@JsonProperty("login")
	private String login;
//	@XmlElement
//	@JsonProperty("status")
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
