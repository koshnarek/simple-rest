package simple;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LinkDTO {

	public static String BASE_URI = "/rs";
	public static String SELF = "self";
	public static String PREVIOUS = "previous";
	public static String NEXT = "next";
	public static String PAGEABLE_QUERY = "%s?page=%d";

	public LinkDTO(String rel, String href) {
		this.setRel(rel);
		this.setHref(href);
	}

	public LinkDTO(String href) {
		this(SELF, href);
	}

	private String rel;

	private String href;

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = BASE_URI + href;
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
