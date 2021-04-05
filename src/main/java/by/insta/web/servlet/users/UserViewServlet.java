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
import java.util.NoSuchElementException;

@WebServlet(urlPatterns = "/userView", name = "UserViewServlet")
public class UserViewServlet extends HttpServlet {

    private PostService postService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute("userService");
        this.postService = (PostService) getServletContext().getAttribute("postService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");

        User user;

        try {
            user = userService.getUserByLogin(login);
        } catch (NoSuchElementException e){
            resp.sendRedirect("/");
            return;
        }
        List<Post> posts = postService.getAllByUser(user);

        req.setAttribute("posts", posts);
        req.setAttribute("user", user);
        req.getServletContext().getRequestDispatcher("/pages/user/view_user.jsp").forward(req, resp);
    }
}
