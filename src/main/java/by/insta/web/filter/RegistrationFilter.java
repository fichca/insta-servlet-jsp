package by.insta.web.filter;

import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsURLPatterns;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        servletNames = {ConstantsNameServlet.REGISTRATION_SERVLET_NAME, ConstantsNameServlet.AUTHORIZATION_SERVLET_NAME},
        filterName = ConstantsNameServlet.REGISTRATION_FILTER_NAME)
public class RegistrationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req.getSession().getAttribute("user") == null) {

            req.setAttribute("isErrors", false);
            chain.doFilter(req, res);

        } else {
            res.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
        }
    }
}
