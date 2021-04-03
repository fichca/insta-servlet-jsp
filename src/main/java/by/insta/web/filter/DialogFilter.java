package by.insta.web.filter;

import by.insta.dao.DialogueStorageImpl;
import by.insta.dao.MessageStorageImpl;
import by.insta.dao.UserStorageImpl;
import by.insta.entity.User;
import by.insta.service.DialogService;
import by.insta.service.DialogServiceImpl;
import by.insta.service.UserService;
import by.insta.service.UserServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(servletNames = {"ViewDialogServlet", "AddDialogServlet", "AddMessageServlet"}, filterName = "DialogFilter")
public class DialogFilter extends HttpFilter {

    private DialogService dialogService;
    private UserService userService;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {


        String loginByFistUser = req.getParameter("fistUser");
        String loginBySecondUser = req.getParameter("secondUser");

        User fistUser = userService.getUserByLogin(loginByFistUser);
        User secondUser = userService.getUserByLogin(loginBySecondUser);

        if (!dialogService.contains(fistUser, secondUser)) {
            req.getServletContext().getRequestDispatcher("/addDialog").forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }


    @Override
    public void init() throws ServletException {
        this.dialogService = (DialogService) getServletContext().getAttribute("dialogService");
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }
}
