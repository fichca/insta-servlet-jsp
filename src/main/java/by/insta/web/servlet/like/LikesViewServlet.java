package by.insta.web.servlet.like;

import by.insta.dao.LikeStorageImpl;
import by.insta.entity.Like;
import by.insta.service.LikeService;
import by.insta.service.LikeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( urlPatterns = "/likesView", name = "LikesViewServlet")
public class LikesViewServlet extends HttpServlet {
    LikeService likeService = new LikeServiceImpl(new LikeStorageImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String postId = req.getParameter("postId");
        List<Like> allLikesByPostId = likeService.getAllLikesByPostId(Integer.parseInt(postId));

        req.setAttribute("likes", allLikesByPostId);
        req.getServletContext().getRequestDispatcher("/pages/like/like_view.jsp").forward(req, resp);
    }
}
