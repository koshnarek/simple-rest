package simple.base;

import java.util.Collection;

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

}
