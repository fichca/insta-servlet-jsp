package by.insta.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"DialogFilter"}, filterName = "DoPostFilter")

public class DoPostFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String method = req.getMethod();
        if (method.equals("GET")) {
            res.sendRedirect("/");
        }else {
            chain.doFilter(req, res);
        }
    }
}
