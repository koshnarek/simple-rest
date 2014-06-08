package simple.users;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import simple.Deletable;
import simple.Salvable;
import simple.Updatable;
import simple.base.AbstractDomain;
import simple.base.Page;
import simple.exceptions.AlreadyExistsException;
import simple.exceptions.EmptyCollectionException;
import simple.exceptions.NotFoundException;
import simple.exceptions.NottingChangeException;

@Entity
@Table(name = "user")
public class User extends AbstractDomain<UserDTO> implements Salvable<User>, Updatable<User>, Deletable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_user")
	private Long id;

	@Column(name = "nam_login")
	private String login;

	@ManyToOne
	@JoinColumn(name = "cod_user_status")
	private Status status;

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
	
	public Status getStatusEntity() {
		return this.status;
	}

	public Character getStatus() {
		if (status != null) {
			return status.getCode();
		}
		return null;
	}

	public void setStatus(Character code) {
		this.status = new Status(code);
	}
	
	public User withId(Long id) {
		this.setId(id);
		return this;
	}

	public User withLogin(String login) {
		this.setLogin(login);
		return this;
	}

	public User withStatus(Character status) {
		this.status = new Status(status);
		return this;
	}

	public static User getNewInstanceFromDTO(UserDTO userDTO) {
		User user = new User();
		user.populateFrom(userDTO);
		return user;
	}

	public static User getNewInstance() {
		return new User();
	}

	public static User find(@NotNull Long id) throws NotFoundException {
		return UserService.getInstance().find(id);
	}

	public static Page<User> findAll(Integer page) throws EmptyCollectionException {
		return UserService.getInstance().findAll(page);
	}

	@Override
	public User save() throws AlreadyExistsException {
		return UserService.getInstance().create(this);
	}

	@Override
	public User update() throws NotFoundException, NottingChangeException {
		return UserService.getInstance().update(this);
	}
	
	@Override
	public void delete() throws NotFoundException {
		UserService.getInstance().delete(this.getId());
	}

	public static Collection<User> getNewInstanceFromDTOs(Collection<UserDTO> userDTOs) {
		Collection<User> users = new ArrayList<User>(userDTOs.size());
		userDTOs.forEach(userDTO -> users.add(User.getNewInstanceFromDTO(userDTO)));
		return users;
	}
}
