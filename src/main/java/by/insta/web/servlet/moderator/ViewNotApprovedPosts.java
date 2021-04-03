package by.insta.web.servlet.moderator;

import by.insta.dao.LikeStorageImpl;
import by.insta.dao.PostStorageImpl;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.service.PostService;
import by.insta.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( urlPatterns = "/post/view/notApproved", name = "ApprovePostServlet")
public class ViewNotApprovedPosts extends HttpServlet {

    private  PostService postService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> allNotApproved = postService.getAllNotApprove();

        req.setAttribute("posts", allNotApproved);
        req.getServletContext().getRequestDispatcher("/pages/moderator/all_not_approved_posts.jsp").forward(req, resp);
    }
}
