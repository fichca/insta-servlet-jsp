package by.insta.web.servlet.moderator;

import by.insta.entity.Post;
import by.insta.service.PostService;
import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsURLPatterns;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ConstantsURLPatterns.APPROVED_POSTS_SERVLET_URL, name = ConstantsNameServlet.APPROVED_POSTS_SERVLET_NAME)
public class ApprovedPostsServlet extends HttpServlet {
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
        resp.sendRedirect(ConstantsURLPatterns.VIEW_NOT_APPROVED_POSTS_SERVLET_URL);
    }
}
