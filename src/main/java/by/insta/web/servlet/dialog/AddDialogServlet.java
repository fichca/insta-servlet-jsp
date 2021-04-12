package by.insta.web.servlet.dialog;

import by.insta.service.DialogService;
import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsServiceName;
import by.insta.web.constans.ConstantsURLPatterns;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ConstantsURLPatterns.ADD_DIALOG_SERVLET_URL, name = ConstantsNameServlet.ADD_DIALOG_SERVLET_NAME)
public class AddDialogServlet extends HttpServlet {

    private DialogService dialogService;

    @Override
    public void init() throws ServletException {
        this.dialogService = (DialogService) getServletContext().getAttribute(ConstantsServiceName.DIALOG_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginByFistUser = req.getParameter("fistUser");
        String loginBySecondUser = req.getParameter("secondUser");
        if (dialogService.addDialog(loginByFistUser, loginBySecondUser)){
            req.getServletContext().getRequestDispatcher(ConstantsURLPatterns.VIEW_DIALOG_SERVLET_URL).forward(req, resp);
        } else {
            resp.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
        }
    }
}
