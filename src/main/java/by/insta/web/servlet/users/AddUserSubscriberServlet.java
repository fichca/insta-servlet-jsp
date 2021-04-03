package by.insta.web.servlet.users;

import by.insta.dao.UserStorageImpl;
import by.insta.entity.User;
import by.insta.service.UserService;
import by.insta.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/addSubscriber", name = "AddUserSubscriberServlet")
public class AddUserSubscriberServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        User userToSubscribe= userService.getUserByLogin(login);
        User subscriber = (User) req.getSession().getAttribute("user");
        userService.addSubscriber(userToSubscribe, subscriber);

        req.getServletContext().getRequestDispatcher("/userView").forward(req, resp);
    }
}
