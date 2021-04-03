package by.insta.web.servlet.comment;

import by.insta.dao.CommentStorageImpl;
import by.insta.entity.Comment;
import by.insta.entity.User;
import by.insta.service.CommentService;
import by.insta.service.CommentServiceImpl;
import by.insta.service.DialogService;
import by.insta.web.servlet.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addComment", name = "AddCommentServlet")
public class AddCommentServlet extends HttpServlet {

    private CommentService commentService;

    @Override
    public void init() throws ServletException {
        this.commentService = (CommentService) getServletContext().getAttribute("commentService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String postId = req.getParameter("postId");
        String comment = req.getParameter("comment");
        if (Util.isEmpty(comment)){
            resp.sendRedirect("/viewPost?id=" + postId);
            return;
        }
        User user = (User) req.getSession().getAttribute("user");

        commentService.add(new Comment(Integer.parseInt(postId), comment, user));

        resp.sendRedirect("/viewPost?id=" + postId);
    }
}
