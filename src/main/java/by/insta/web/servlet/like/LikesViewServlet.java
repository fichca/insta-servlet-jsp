package by.insta.web.servlet.like;

import by.insta.entity.Like;
import by.insta.service.LikeService;
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

@WebServlet( urlPatterns = ConstantsURLPatterns.LIKES_VIEW_SERVLET_URL, name = ConstantsNameServlet.LIKES_VIEW_SERVLET_NAME)
public class LikesViewServlet extends HttpServlet {

    private LikeService likeService;

    @Override
    public void init() throws ServletException {
        this.likeService = (LikeService) getServletContext().getAttribute("likeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String postId = req.getParameter("postId");
        List<Like> allLikesByPostId = likeService.getAllLikesByPostId(Integer.parseInt(postId));

        req.setAttribute("likes", allLikesByPostId);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.LIKE_VIEW_PATH).forward(req, resp);
    }
}
