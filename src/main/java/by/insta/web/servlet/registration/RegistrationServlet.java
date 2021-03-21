package by.insta.web.servlet.registration;

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

@WebServlet(urlPatterns = "/registration", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl(new UserStorageImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/registration/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        User user = new User(name, login, password);

        if (!userService.addUser(user)){
            req.setAttribute("isErrors", true);
            String result = "Login already use!";
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher("/registration/registration.jsp").forward(req, resp);
        }else {
            resp.sendRedirect("/auth");
        }


    }
}
