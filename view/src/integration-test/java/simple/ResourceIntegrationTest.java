package simple;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResourceIntegrationTest<T> {

	private T item;

	private ErrorIntegrationTest error;

	private Collection<T> items;

	private Collection<LinkIntegrationTest> links;

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public ErrorIntegrationTest getError() {
		return error;
	}

	public void setError(ErrorIntegrationTest error) {
		this.error = error;
	}

	public Collection<T> getItems() {
		return items;
	}

	public void setItems(Collection<T> items) {
		this.items = items;
	}

	public Collection<LinkIntegrationTest> getLinks() {
		return links;
	}

	public void setLinks(Collection<LinkIntegrationTest> links) {
		this.links = links;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
