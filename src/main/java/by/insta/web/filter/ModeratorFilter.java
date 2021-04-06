package by.insta.web.filter;

import by.insta.entity.Role;
import by.insta.entity.User;
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
        servletNames = ConstantsNameServlet.APPROVED_POSTS_SERVLET_NAME, filterName = ConstantsNameServlet.MODERATOR_FILTER_NAME)
public class ModeratorFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            res.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
        } else if (user.getRole() != Role.MODERATOR) {
            res.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
        } else
            chain.doFilter(req, res);
    }
}

