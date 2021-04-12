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

@WebServlet(urlPatterns = ConstantsURLPatterns.USER_UPDATE_NAME_SERVLET_URL, name = ConstantsNameServlet.USER_UPDATE_NAME_SERVLET_NAME)
public class UserUpdateNameServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute(ConstantsServiceName.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", false);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.UPDATE_NAME_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", true);
        User user = (User) req.getSession().getAttribute("user");
        String newName = req.getParameter("New name");

        String validate;
        if (userService.updateNameById(user.getId(), newName)) {
            user.setName(newName);
            validate = "Deal!";
        } else {
            validate = "Invalidate name";
        }
        req.setAttribute("result", validate);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.UPDATE_NAME_PATH).forward(req, resp);
    }
}
