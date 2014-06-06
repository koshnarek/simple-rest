package simple;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import simple.exceptions.DTOConstructionException;

public class AbstractDTO<T> {

	protected LinkDTO link;

	protected void populateFrom(T domain) {
		try {
			BeanUtils.copyProperties(this, domain);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			throw new DTOConstructionException(this, domain, e);
		}
	}

	public LinkDTO getLink() {
		return link;
	}

	public void setLink(String path) {
		this.link = new LinkDTO(path);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
