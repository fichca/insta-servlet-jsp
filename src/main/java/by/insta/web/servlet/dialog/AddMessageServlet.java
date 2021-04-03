package by.insta.web.servlet.dialog;

import by.insta.dao.MessageStorageImpl;
import by.insta.entity.Message;
import by.insta.entity.User;
import by.insta.service.DialogService;
import by.insta.service.MessageService;
import by.insta.service.MessageServiceImpl;
import by.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addMessage", name = "AddMessageServlet")
public class AddMessageServlet extends HttpServlet {
    private  MessageService messageService;

    @Override
    public void init() throws ServletException {
        this.messageService = (MessageService) getServletContext().getAttribute("messageService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        String dialogId = req.getParameter("dialogId");
        String text = req.getParameter("message");

        messageService.addMessage(new Message(Integer.parseInt(dialogId), user, text));

        req.getServletContext().getRequestDispatcher("/dialog/view").forward(req, resp);
    }
}
