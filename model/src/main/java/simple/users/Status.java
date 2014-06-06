package simple.users;

import java.util.Arrays;

import simple.statuses.AbstractStatus;
import simple.users.Status.Type;

public class Status extends AbstractStatus<Type> {

	public Status(Character code) {
		this.type = Type.fromCode(code);
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

	@Override
	public Character getCode() {
		return this.getType() != null ? this.getType().getCode() : null;
	}

}
