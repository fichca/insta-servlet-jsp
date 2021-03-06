package by.insta.web.servlet.users;

import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.service.PostService;
import by.insta.service.UserService;
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
import java.util.NoSuchElementException;

@WebServlet(urlPatterns = ConstantsURLPatterns.USER_VIEW_SERVLET_URL, name = ConstantsNameServlet.USER_VIEW_SERVLET_NAME)
public class UserViewServlet extends HttpServlet {

    private PostService postService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute(ConstantsServiceName.USER_SERVICE);
        this.postService = (PostService) getServletContext().getAttribute(ConstantsServiceName.POST_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        User user;

        try {
            user = userService.getUserById(Integer.parseInt(id));
        } catch (NoSuchElementException e){
            resp.sendRedirect(ConstantsURLPatterns.ALL_POSTS_FEED_SERVLET_URL);
            return;
        }

        List<Post> posts = postService.getAllByUser(user);


        req.setAttribute("posts", posts);
        req.setAttribute("user", user);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.VIEW_USER_PATH).forward(req, resp);
    }

}
