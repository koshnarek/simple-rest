package simple.base;

import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PageDTO<T> {

	private Integer pageIndex;

	private Collection<T> pageCollection;

	private Integer totalPages;

	public PageDTO(Integer pageIndex, Integer totalPages, Collection<T> pageCollection) {
		this.withPageIndex(pageIndex);
		this.withTotalPages(totalPages);
		this.withPageCollection(pageCollection);
	}

	public Collection<T> getPageCollection() {
		return pageCollection;
	}

	public PageDTO<T> withPageCollection(Collection<T> pageCollection) {
		this.pageCollection = pageCollection;
		return this;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public PageDTO<T> withTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
		return this;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public PageDTO<T> withPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
		return this;
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
