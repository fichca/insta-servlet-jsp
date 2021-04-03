package by.insta.web.servlet.users;

import by.insta.dao.LikeStorageImpl;
import by.insta.dao.PostStorageImpl;
import by.insta.dao.UserStorageImpl;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.service.PostService;
import by.insta.service.PostServiceImpl;
import by.insta.service.UserService;
import by.insta.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userAccount/allPosts", name = "AllPostByUserServlet")
public class AllPostByUserServlet extends HttpServlet {
    private PostService postService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute("postService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        List<Post> allByUser = postService.getAllByUser(user);

        req.setAttribute("posts", allByUser);

        req.getServletContext().getRequestDispatcher("/pages/user/all_posts_user.jsp").forward(req, resp);
    }
}
