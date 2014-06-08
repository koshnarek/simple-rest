package simple;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResourceIntegrationDTO<T> {

	private T item;

	private ErrorIntegrationDTO error;

	private Collection<T> items;

	private Collection<LinkIntegrationDTO> links;

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public ErrorIntegrationDTO getError() {
		return error;
	}

	public void setError(ErrorIntegrationDTO error) {
		this.error = error;
	}

	public Collection<T> getItems() {
		return items;
	}

	public void setItems(Collection<T> items) {
		this.items = items;
	}

	public Collection<LinkIntegrationDTO> getLinks() {
		return links;
	}

	public void setLinks(Collection<LinkIntegrationDTO> links) {
		this.links = links;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
