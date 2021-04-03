package by.insta.web.servlet.registration;

import by.insta.dao.UserStorageImpl;
import by.insta.entity.User;
import by.insta.service.PostService;
import by.insta.service.UserService;
import by.insta.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet(urlPatterns = "/auth", name = "AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/pages/registration/authorization.jsp").forward(req, resp);
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
            req.getServletContext().getRequestDispatcher("/pages/registration/authorization.jsp").forward(req, resp);
            return;
        }

        if (userByLogin.getPassword().equals(password)) {
            req.getSession().setAttribute("user", userByLogin);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("isErrors", true);
            req.setAttribute("result", "Invalid password");
            req.getServletContext().getRequestDispatcher("/pages/registration/authorization.jsp").forward(req, resp);
        }
    }
}
