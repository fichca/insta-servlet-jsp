package by.insta.web.servlet.dialog;

import by.insta.dao.DialogueStorageImpl;
import by.insta.dao.MessageStorageImpl;
import by.insta.dao.UserStorageImpl;
import by.insta.entity.Dialogue;
import by.insta.entity.User;
import by.insta.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/dialog/view", name = "ViewDialogServlet")
public class ViewDialogServlet extends HttpServlet {
    private DialogService dialogService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.dialogService = (DialogService) getServletContext().getAttribute("dialogService");
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginByFistUser = req.getParameter("fistUser");
        String loginBySecondUser = req.getParameter("secondUser");

        User fistUser = userService.getUserByLogin(loginByFistUser);
        User secondUser = userService.getUserByLogin(loginBySecondUser);

        Dialogue dialog = dialogService.getByUsers(fistUser, secondUser);
        req.setAttribute("dialog", dialog);

        req.getServletContext().getRequestDispatcher("/pages/dialog/dialog_view.jsp").forward(req, resp);
    }
}
