package simple.filter;

public class VersionAllowedHolder {

	private static VersionAllowedHolder instance = new VersionAllowedHolder();

	final private ThreadLocal<Integer> allowedVersion = new ThreadLocal<Integer>();

	private VersionAllowedHolder() {
		// just to prevent new;
	}

	public static VersionAllowedHolder getInstance() {
		return instance;
	}

	public Integer getVersion() {
		return this.allowedVersion.get();
	}

	public void setVersion(Integer version) {
		this.allowedVersion.set(version);
	}
	
	public void remove() {
		this.allowedVersion.remove();
	}
}
