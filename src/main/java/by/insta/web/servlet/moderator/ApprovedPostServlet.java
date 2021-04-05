package by.insta.web.servlet.moderator;

import by.insta.dao.LikeStorageImpl;
import by.insta.dao.PostStorageImpl;
import by.insta.entity.Post;
import by.insta.service.LikeService;
import by.insta.service.PostService;
import by.insta.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/post/approved", name = "ApprovedPostServlet")
public class ApprovedPostServlet extends HttpServlet {
    private  PostService postService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute("postService");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("id");
        Post byId = postService.getById(Integer.parseInt(postId));
        byId.setApproved(true);
        resp.sendRedirect("/post/view/notApproved");
    }
}
