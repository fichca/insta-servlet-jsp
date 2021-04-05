package by.insta.web.filter;

import by.insta.entity.Role;
import by.insta.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        servletNames = "ApprovePostServlet",
        filterName = "ModeratorFilter")
public class ModeratorFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            res.sendRedirect("/");
        } else if (user.getRole() != Role.MODERATOR) {
            res.sendRedirect("/");
        } else
            chain.doFilter(req, res);
    }
}

