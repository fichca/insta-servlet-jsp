package by.insta.web.filter;

import by.insta.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"LogOutServlet", "CreatePostServlet", "AddLikeServlet", "AddCommentServlet", "LikesViewServlet", "UserAccountServlet", "UserUpdatePasswordServlet", "UserUpdateNameServlet", "AllPostByUserServlet"})
public class UserSessionFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            res.sendRedirect("/");
        }else {
            chain.doFilter(req, res);
        }
    }
}
