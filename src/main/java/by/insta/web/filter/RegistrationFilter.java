package by.insta.web.filter;

import by.insta.dao.UserStorageImpl;
import by.insta.entity.User;
import by.insta.service.UserService;
import by.insta.service.UserServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(servletNames = {"RegistrationServlet", "AuthorizationServlet"})
public class RegistrationFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req.getSession().getAttribute("user") == null) {

            req.setAttribute("isErrors", false);
            chain.doFilter(req, res);

        } else {
            res.sendRedirect("/");
        }
    }
}
