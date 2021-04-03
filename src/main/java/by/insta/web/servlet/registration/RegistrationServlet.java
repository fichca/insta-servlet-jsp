package by.insta.web.servlet.registration;

import by.insta.dao.UserStorageImpl;
import by.insta.entity.Role;
import by.insta.entity.User;
import by.insta.service.UserService;
import by.insta.service.UserServiceImpl;
import by.insta.web.servlet.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/pages/registration/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String result = validate(name, login, password);

        if (!result.isEmpty()){
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher("/pages/registration/registration.jsp").forward(req, resp);
        }

        User user = new User(name, login, password, Role.USER);

        if (!userService.addUser(user)){
            req.setAttribute("isErrors", true);
            result = "Login already use!";
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher("/pages/registration/registration.jsp").forward(req, resp);
        }else {
            resp.sendRedirect("/auth");
        }
    }


    private String validate(String name, String login, String pass){
        if (Util.isEmpty(name)){
            return "Invalidate name";
        }else if (Util.isEmpty(login)){
            return "Invalidate login";
        }else if (Util.isEmpty(pass)) {
            return "Invalidate password";
        } else return "";
    }
}
