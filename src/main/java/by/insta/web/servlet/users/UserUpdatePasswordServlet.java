package by.insta.web.servlet.users;

import by.insta.entity.User;
import by.insta.service.UserService;
import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsPathJSP;
import by.insta.web.constans.ConstantsServiceName;
import by.insta.web.constans.ConstantsURLPatterns;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ConstantsURLPatterns.USER_UPDATE_PASSWORD_SERVLET_URL, name = ConstantsNameServlet.USER_UPDATE_PASSWORD_SERVLET_NAME)
public class UserUpdatePasswordServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute(ConstantsServiceName.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", false);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.UPDATE_PASSWORD_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", true);

        User user = (User) req.getSession().getAttribute("user");
        String newPassword = req.getParameter("New password");

        String validate;
        if (userService.updatePasswordById(user.getId(), newPassword)) {
            user.setPassword(newPassword);
            validate = "Deal!";
        } else {
            validate = "Invalidate password";
        }
        req.setAttribute("result", validate);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.UPDATE_PASSWORD_PATH).forward(req, resp);
    }
}
