package uol.cubus.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.StopWatch;

import uol.cubus.shared.LogHolder;

public class AccessLoggingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final StopWatch watch = new StopWatch();
		watch.start();
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			chain.doFilter(request, response);
		} finally {
			watch.stop();
			LogHolder.getLogger().info("layer=view httpMethod={} uri={} elapsedTime={} remoteHost={} httpStatus={}",
					httpRequest.getMethod(), httpRequest.getRequestURI(), watch.getTime(), httpRequest.getRemoteHost(),
					httpResponse.getStatus());
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
