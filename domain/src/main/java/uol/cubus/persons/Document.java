package uol.cubus.persons;

public class Document {

	private String code;

	private Type type;

	public enum Type {
		CPF, RG, CNPJ, IE
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
