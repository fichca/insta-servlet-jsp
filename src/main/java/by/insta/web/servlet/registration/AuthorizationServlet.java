package by.insta.web.servlet.registration;

import by.insta.entity.User;
import by.insta.service.UserService;
import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsPathJSP;
import by.insta.web.constans.ConstantsURLPatterns;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet(urlPatterns = ConstantsURLPatterns.AUTHORIZATION_SERVLET_URL, name = ConstantsNameServlet.AUTHORIZATION_SERVLET_NAME)
public class AuthorizationServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.AUTHORIZATION_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User userByLogin;

        try {
            userByLogin = userService.getUserByLogin(login);

        } catch (NoSuchElementException e) {
            req.setAttribute("isErrors", true);
            req.setAttribute("result", "Invalid login");
            req.getServletContext().getRequestDispatcher(ConstantsPathJSP.AUTHORIZATION_PATH).forward(req, resp);
            return;
        }

        if (userByLogin.getPassword().equals(password)) {
            req.getSession().setAttribute("user", userByLogin);
            resp.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
        } else {
            req.setAttribute("isErrors", true);
            req.setAttribute("result", "Invalid password");
            req.getServletContext().getRequestDispatcher(ConstantsPathJSP.AUTHORIZATION_PATH).forward(req, resp);
        }
    }
}
