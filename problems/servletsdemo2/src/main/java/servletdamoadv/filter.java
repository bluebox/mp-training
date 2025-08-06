package servletdamoadv;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
@WebFilter("/first")
public class filter implements Filter {

   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	PrintWriter out=response.getWriter();
    	HttpServletRequest req=(HttpServletRequest) request;
    	if(Integer.parseInt(req.getParameter("input"))>0) {
        chain.doFilter(request, response);
    	}else {
    		out.println("Id should be Greater than 0 and a digit");
    	}
    }


}
