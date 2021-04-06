package by.insta.web.servlet.post;

import by.insta.Constant;
import by.insta.entity.Comment;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.service.*;
import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsPathJSP;
import by.insta.web.constans.ConstantsURLPatterns;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = ConstantsURLPatterns.POST_VIEW_SERVLET_URL, name = ConstantsNameServlet.POST_VIEW_SERVLET_NAME)
public class PostViewServlet extends HttpServlet {

    private PostService postService;
    private CommentService commentService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute("postService");
        this.commentService = (CommentService) getServletContext().getAttribute("commentService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        int postId = Integer.parseInt(id);
        Post post = postService.getById(postId);

        if (!post.isApproved()){
            resp.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
        }

        String commentPageString = req.getParameter("commentPage");

        if (commentPageString == null) {
            commentPageString = "1";
        }

        int commentPage = Integer.parseInt(commentPageString);
        int start = (commentPage - 1) * Constant.COMMENT_ON_PAGE;
        List<Comment> commentsPage = commentService.getCommentsPage(start, postId);
        int countCommentPages = commentService.getCountCommentsPage(postId);
        req.setAttribute("numberCommentPage", commentPage);
        req.setAttribute("countCommentPages", countCommentPages);

        setPostInAttribute(req, commentsPage, post);

        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.POST_VIEW_PATH).forward(req, resp);
    }

    private void setPostInAttribute(HttpServletRequest req, List<Comment> commentsPage, Post post){
        User user = (User) req.getSession().getAttribute("user");
        addView(user, post);
        post.setComments(commentsPage);
       req.setAttribute("post", post);
    }

    private void addView(User user, Post post){
        if (user == null) return;
        int id = user.getId();
        List<Integer> countView = post.getCountView();
        if (!countView.contains(id)){
            countView.add(id);
        }
    }


}
