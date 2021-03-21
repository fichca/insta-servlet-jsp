package by.insta.web.servlet.like;

import by.insta.dao.LikeStorage;
import by.insta.dao.LikeStorageImpl;
import by.insta.entity.Like;
import by.insta.entity.User;
import by.insta.service.LikeService;
import by.insta.service.LikeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addLike", name = "AddLikeServlet")

public class AddLikeServlet extends HttpServlet {

    LikeService likeService = new LikeServiceImpl(new LikeStorageImpl());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String postId = req.getParameter("postId");
        User user = (User) req.getSession().getAttribute("user");

        boolean addLike = likeService.addLike(new Like(Integer.parseInt(postId), user));
        if (!addLike){
            likeService.deleteLikeByUser(user);
        }
        resp.sendRedirect("/viewPost?id=" + postId);
    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        String postId = req.getParameter("postId");
//        User user = (User) req.getSession().getAttribute("user");
//        if (name == null){
//
//            return;
//        }
//
//        boolean addLike = likeService.addLike(new Like(Integer.parseInt(postId), user));
//        if (!addLike){
//            likeService.deleteLikeByUser(user);
//        }
//
//
//        resp.sendRedirect("/viewPost?id=" + postId);
//    }
}
