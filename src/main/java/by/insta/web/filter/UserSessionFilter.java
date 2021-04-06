package by.insta.web.filter;

import by.insta.entity.User;
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
        servletNames = {LOG_OUT_SERVLET_NAME, CREATE_POST_SERVLET_NAME, ADD_LIKE_SERVLET_NAME, ADD_COMMENT_SERVLET_NAME, LIKES_VIEW_SERVLET_NAME, USER_ACCOUNT_SERVLET_NAME, USER_UPDATE_PASSWORD_SERVLET_NAME, USER_UPDATE_NAME_SERVLET_NAME, ALL_POSTS_BY_USER_SERVLET_NAME, ADD_USER_SUBSCRIBER_SERVLET_NAME, DELETE_USER_SUBSCRIBER_SERVLET_NAME, ALL_POSTS_FEED_BY_SUBSCRIPTIONS_SERVLET_NAME,
                VIEW_DIALOG_SERVLET_NAME, ADD_DIALOG_SERVLET_NAME, ADD_MESSAGE_SERVLET_NAME, APPROVED_POSTS_SERVLET_NAME},
        filterName = USER_SESSION_FILTER_NAME)
public class UserSessionFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            res.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
        }else {
            chain.doFilter(req, res);
        }
    }
}
