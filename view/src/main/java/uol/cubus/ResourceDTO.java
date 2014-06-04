package uol.cubus;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import uol.cubus.exceptions.ErrorDTO;

//@XmlRootElement
public class ResourceDTO {

	// @XmlElement
	private AbstractDTO<?> item;

	// @XmlElement
	private ErrorDTO error;

	// @XmlElement
	private Collection<AbstractDTO<?>> items;

	// @XmlElement
	private Collection<LinkDTO> links;

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

	public static ResourceDTO fromDTO(AbstractDTO<?> item) {
		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setItem(item);
		return resourceDTO;
	}

	public static ResourceDTO fromDTOs(Collection<AbstractDTO<?>> items, int page, int size) {
		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setItems(items);
		String href = items.stream().findFirst().get().getLink().getHref().replace(LinkDTO.BASE_URI, "");
		resourceDTO.addLink(LinkDTO.NEXT, String.format(LinkDTO.PAGEABLE_QUERY, href, page++, size));
		resourceDTO.addLink(LinkDTO.PREVIOUS, String.format(LinkDTO.PAGEABLE_QUERY, href, page--, size));
		return resourceDTO;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
