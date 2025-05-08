package chapter6.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/editMessage","/setting"})
public class LoginFilter implements  Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//型変換(キャスト)してsessionに代入
		HttpSession session = ((HttpServletRequest)request).getSession();
		Object loginCheck = session.getAttribute("loginUser");
		List<String> errorMessages = new ArrayList<String>();

		if(loginCheck != null){
			chain.doFilter(request, response);
		}else {
			errorMessages.add("ログインしてください");
			session.setAttribute("errorMessages", errorMessages);
			((HttpServletResponse)response).sendRedirect("login");
			return;
		}

	}
	public void init(FilterConfig config) {
	}
	public void destroy() {
	}
}
