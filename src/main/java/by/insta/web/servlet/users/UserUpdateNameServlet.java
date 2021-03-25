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

@WebServlet(urlPatterns = "/userAccount/updateName", name = "UserUpdateNameServlet")
public class UserUpdateNameServlet extends HttpServlet {

    UserService userService = new UserServiceImpl(new UserStorageImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", false);
        req.getServletContext().getRequestDispatcher("/pages/user/update_name.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", true);
        User user = (User) req.getSession().getAttribute("user");
        String newName = req.getParameter("New name");

        String validate;
        if (userService.updateNameById(user.getId(), newName)) {
            validate = "Deal!";
        } else {
            validate = "Invalidate name";
        }
        req.setAttribute("result", validate);
        req.getServletContext().getRequestDispatcher("/pages/user/update_name.jsp").forward(req, resp);
    }
}
