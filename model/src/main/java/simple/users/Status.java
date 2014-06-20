package simple.users;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import simple.base.AbstractStatus;
import simple.users.Status.Type;

@Entity
@Table(name = "user_status")
public class Status extends AbstractStatus<Type, Status> {

	@Id
	@Column(name = "cod_status")
	public Character id;

	@Column(name = "nam_status")
	public String description;

	public Status() {
	}

	public Status(Character id) {
		if (id != null) {
			this.type = Type.fromCode(id);
			this.id = this.type.getCode();
			this.description = this.type.name();
		}
	}

	public Character getId() {
		return id;
	}

	public void setId(Character id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public enum Type {

		ACTIVE('A'), BLOCKED('B');

		private Character code;

		Type(char code) {
			this.code = code;
		}

		public Character getCode() {
			return code;
		}

		public static Type fromCode(Character code) {
			return Arrays.asList(Type.values())
					.stream()
					.filter(statusType -> statusType.getCode().equals(code))
					.findFirst()
					.orElse(null);
		}
	}

}
