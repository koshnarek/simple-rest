package simple.base;

import java.util.Collection;

public class Page<T> extends AbstractDomain<PageDTO<?>> {

	final public static int SIZE = 5;

	final public static String PARAM = "page";

	private Collection<T> pageCollection;

	private Integer totalPages;

	public Page(Collection<T> pageCollection, Integer totalPages) {
		this.setPageCollection(pageCollection);
		this.setTotalPages(totalPages);
	}

	public Collection<T> getPageCollection() {
		return pageCollection;
	}

	public void setPageCollection(Collection<T> pageCollection) {
		this.pageCollection = pageCollection;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
