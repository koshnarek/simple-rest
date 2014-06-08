package simple.base;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResourceDTO<T> {

	private T item;

	private ErrorDTO error;

	private Collection<T> items;

	private Integer totalPages;

	private Collection<LinkDTO> links;

	@SuppressWarnings("unchecked")
	public ResourceDTO(T obj) {
		if (obj instanceof ErrorDTO) {
			this.setError((ErrorDTO) obj);
		} else if (obj instanceof PageDTO) {
			PageDTO<T> pageDTO = (PageDTO<T>) obj;
			this.setItems(pageDTO.getPageCollection());
			this.setTotalPages(pageDTO.getTotalPages());
		} else {
			this.setItem(obj);
		}
	}

	public ResourceDTO(T obj, Integer page) {
		this(obj);
		if (!items.isEmpty()) {
			page++;
			LinkDTO linkDTO = ((AbstractDTO<?>) items.stream().findFirst().get()).getLink();
			if (linkDTO != null) {
				String href = "/" + linkDTO.getHref().replace(LinkDTO.BASE_URI, "").replaceAll("[/0-9]*", "");
				this.addLink(LinkDTO.NEXT, String.format(LinkDTO.PAGEABLE_QUERY, href, page));
				if ((page - 2) > 0) {
					this.addLink(LinkDTO.PREVIOUS, String.format(LinkDTO.PAGEABLE_QUERY, href, page - 1));
				}
			}
		}
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

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
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
