package simple;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import simple.exceptions.ModelConstructionException;

public abstract class AbstractDomain<T> {
	
	protected void populateFrom(T dto) {
		try {
			BeanUtils.copyProperties(this, dto);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			throw new ModelConstructionException(this, dto, e);
		}
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