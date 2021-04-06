package by.insta.web.filter;

import by.insta.web.constans.ConstantsURLPatterns;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.insta.web.constans.ConstantsNameServlet.*;

@WebFilter(
        servletNames = {VIEW_DIALOG_SERVLET_NAME, ADD_DIALOG_SERVLET_NAME, ADD_MESSAGE_SERVLET_NAME}, filterName = DO_POST_FILTER_NAME)

public class DoPostFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String method = req.getMethod();
        if (method.equals("GET")) {
            res.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
        }else {
            chain.doFilter(req, res);
        }
    }
}
