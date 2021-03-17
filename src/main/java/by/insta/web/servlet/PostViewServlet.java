package by.insta.web.servlet;

import by.insta.Constant;
import by.insta.dao.CommentStorageImpl;
import by.insta.dao.PostStorageImpl;
import by.insta.entity.Comment;
import by.insta.entity.Post;
import by.insta.service.CommentService;
import by.insta.service.CommentServiceImpl;
import by.insta.service.PostService;
import by.insta.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@WebServlet(urlPatterns = "/viewPost")
public class PostViewServlet extends HttpServlet {

//    public static void main(String[] args) {
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        LocalDate ld = LocalDate.now();
//        LocalTime localTime = LocalTime.now();
//    }

    PostService postService = new PostServiceImpl(new PostStorageImpl());
    CommentService commentService = new CommentServiceImpl(new CommentStorageImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String commentPageString = req.getParameter("commentPage");

        if (commentPageString == null) {
            commentPageString = "1";
        }

        int postId = Integer.parseInt(id);


        int commentPage = Integer.parseInt(commentPageString);

        int start = (commentPage - 1) * Constant.COMMENT_ON_PAGE;

        List<Comment> commentsPage = commentService.getCommentsPage(start, postId);
        int countCommentPages = commentService.getCountCommentsPage(postId);

        Post post = postService.getById(postId);

        post.setComments(commentsPage);


        req.setAttribute("numberCommentPage", commentPage);
        req.setAttribute("countCommentPages", countCommentPages);
        req.setAttribute("post", post);

        req.getServletContext().getRequestDispatcher("/pages/post_view.jsp").forward(req, resp);
    }
}
