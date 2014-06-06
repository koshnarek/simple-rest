package simple;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResourceDTO {

	private AbstractDTO<?> item;

	private ErrorDTO error;

	private Collection<AbstractDTO<?>> items;

	private Collection<LinkDTO> links;

	public Collection<LinkDTO> getLinks() {
		return links;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	public AbstractDTO<?> getItem() {
		return this.item;
	}

	public void setItem(AbstractDTO<?> item) {
		if (item instanceof ErrorDTO) {
			this.setError((ErrorDTO) item);
		} else {
			this.item = item;
		}
	}

	public Collection<AbstractDTO<?>> getItems() {
		return items;
	}

	public void setItems(Collection<AbstractDTO<?>> items) {
		this.items = items;
	}

	public ResourceDTO addLink(String rel, String href) {
		if (this.links == null) {
			this.links = new ArrayList<LinkDTO>();
		}
		this.links.add(new LinkDTO(rel, href));
		return this;
	}

	public static ResourceDTO getInstanceFrom(AbstractDTO<?> item) {
		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setItem(item);
		return resourceDTO;
	}

	public static ResourceDTO getInstanceFrom(Collection<AbstractDTO<?>> items) {
		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setItems(items);
		return resourceDTO;
	}

	public static ResourceDTO getInstanceFrom(Collection<AbstractDTO<?>> items, int page, boolean hasNext, boolean hasPrevious) {
		ResourceDTO resourceDTO = ResourceDTO.getInstanceFrom(items);
		String href = items.stream().findFirst().get().getLink().getHref().replace(LinkDTO.BASE_URI, "");
		if (hasNext)
			resourceDTO.addLink(LinkDTO.NEXT, String.format(LinkDTO.PAGEABLE_QUERY, href, page + 1, items.size()));
		if (hasPrevious)
			resourceDTO.addLink(LinkDTO.PREVIOUS, String.format(LinkDTO.PAGEABLE_QUERY, href, page - 1, items.size()));
		return resourceDTO;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}