package simple.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import simple.AbstractDomain;
import simple.exceptions.AlreadyExistsException;
import simple.exceptions.NotFoundException;

@Entity
@Table(name = "user_all")
public class User extends AbstractDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_user_all")
	private Long id;

	@Column(name = "nam_login")
	private String login;

	@Column(name = "cod_user_status")
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static User getNewInstance() {
		return new User();
	}

	public static User find(@NotNull Long id) throws NotFoundException {
		return (new User()).withId(id).withLogin("x@x.com").withStatus('A');
		//return UserService.getInstance().find(id);
		//throw new NotFoundException(UserError.NOT_FOUND.withId(id));
	}

	public User create() throws AlreadyExistsException {
		return UserService.getInstance().create(this);
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
		this.setStatus(new Status(status));
		return this;
	}

}
