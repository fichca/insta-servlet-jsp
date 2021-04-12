package by.insta.web.servlet.comment;

import by.insta.entity.Comment;
import by.insta.entity.User;
import by.insta.service.CommentService;
import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsServiceName;
import by.insta.web.constans.ConstantsURLPatterns;
import by.insta.web.servlet.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ConstantsURLPatterns.ADD_COMMENT_SERVLET_URL, name = ConstantsNameServlet.ADD_COMMENT_SERVLET_NAME)

public class AddCommentServlet extends HttpServlet {

    private CommentService commentService;

    private final String parameterComment = "comment";
    private final String parameterPostId = "postId";

    private final String attributeUser = "user";
    private final String redirectId = "?id=";


    @Override
    public void init() throws ServletException {
        this.commentService = (CommentService) getServletContext().getAttribute(ConstantsServiceName.COMMENT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String postId = req.getParameter(parameterPostId);
        String comment = req.getParameter(parameterComment);
        if (Util.isEmpty(comment)){
            resp.sendRedirect(ConstantsURLPatterns.POST_VIEW_SERVLET_URL + redirectId + postId);
            return;
        }

        User user = (User) req.getSession().getAttribute(attributeUser);
        commentService.add(new Comment(Integer.parseInt(postId), comment, user));
        resp.sendRedirect(ConstantsURLPatterns.POST_VIEW_SERVLET_URL + redirectId + postId);
    }
}
