package by.insta.web.servlet.dialog;

import by.insta.entity.Comment;
import by.insta.entity.Dialogue;
import by.insta.entity.User;
import by.insta.service.*;
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

@WebServlet(urlPatterns = ConstantsURLPatterns.VIEW_DIALOG_SERVLET_URL, name = ConstantsNameServlet.VIEW_DIALOG_SERVLET_NAME)

public class ViewDialogServlet extends HttpServlet {
    private DialogService dialogService;

    @Override
    public void init() throws ServletException {
        this.dialogService = (DialogService) getServletContext().getAttribute(ConstantsServiceName.DIALOG_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginByFistUser = req.getParameter("fistUser");
        String loginBySecondUser = req.getParameter("secondUser");


        Dialogue dialog = dialogService.getByUsers(loginByFistUser, loginBySecondUser);
        req.setAttribute("dialog", dialog);

        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.DIALOG_VIEW_PATH).forward(req, resp);
    }
}
