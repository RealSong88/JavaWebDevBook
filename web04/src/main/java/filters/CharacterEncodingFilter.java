package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{

	FilterConfig config;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
			throws IOException, ServletException {
		// 서블릿을 실행하기 전에 수행할 작업
		request.setCharacterEncoding("UTF-8");
		
		// 다음 필터를 호출. 더이상 필터가 없다면 서블릿의 service()가 호출됨.
		nextFilter.doFilter(request, response);
		// 서블릿을 실행한 후 수행할 작업

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
