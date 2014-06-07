package simple.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class VersionFilter implements Filter {

	final public static String NAME = "X-version";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getHeader(NAME) != null) {
			VersionAllowedHolder.getInstance().setVersion(Integer.valueOf(httpRequest.getHeader(NAME)));
		}
		chain.doFilter(request, response);
		VersionAllowedHolder.getInstance().remove();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
