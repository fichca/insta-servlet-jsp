package by.insta.web.servlet.moderator;

import by.insta.entity.Post;
import by.insta.service.PostService;
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
import java.util.List;

@WebServlet( urlPatterns = ConstantsURLPatterns.VIEW_NOT_APPROVED_POSTS_SERVLET_URL, name = ConstantsNameServlet.VIEW_NOT_APPROVED_POSTS_SERVLET_NAME)
public class ViewNotApprovedPosts extends HttpServlet {

    private  PostService postService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute(ConstantsServiceName.POST_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> allNotApproved = postService.getAllNotApprove();

        req.setAttribute("posts", allNotApproved);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.ALL_NOT_APPROVED_PATH).forward(req, resp);
    }
}
