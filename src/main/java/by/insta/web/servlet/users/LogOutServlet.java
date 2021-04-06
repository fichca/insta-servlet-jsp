package by.insta.web.servlet.users;


import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsURLPatterns;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ConstantsURLPatterns.LOG_OUT_SERVLET_URL, name = ConstantsNameServlet.LOG_OUT_SERVLET_NAME)

public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();
        resp.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
    }
}
