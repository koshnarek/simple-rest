package simple;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResourceDTO<T> {

	private T item;

	private ErrorDTO error;

	private Collection<T> items;

	private Collection<LinkDTO> links;

	@SuppressWarnings("unchecked")
	public ResourceDTO(T obj) {
		if (obj instanceof ErrorDTO) {
			this.setError((ErrorDTO) obj);
		} else if (obj instanceof Collection) {
			this.setItems((Collection<T>) obj);
		} else {
			this.setItem(obj);
		}
	}

	public ResourceDTO(T obj, Integer page, boolean hasNext, boolean hasPrevious) {
		this(obj);
		String href = ((AbstractDTO<?>)items.stream().findFirst().get()).getLink().getHref().replace(LinkDTO.BASE_URI, "");
		if (hasNext)
			this.addLink(LinkDTO.NEXT, String.format(LinkDTO.PAGEABLE_QUERY, href, page + 1, items.size()));
		if (hasPrevious)
			this.addLink(LinkDTO.PREVIOUS, String.format(LinkDTO.PAGEABLE_QUERY, href, page - 1, items.size()));
	}

	public Collection<LinkDTO> getLinks() {
		return links;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	public T getItem() {
		return this.item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public Collection<T> getItems() {
		return items;
	}

	public void setItems(Collection<T> items) {
		this.items = items;
	}

	public ResourceDTO<T> addLink(String rel, String href) {
		if (this.links == null) {
			this.links = new ArrayList<LinkDTO>();
		}
		this.links.add(new LinkDTO(rel, href));
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
