package by.insta.web.servlet.users;

import by.insta.entity.Post;
import by.insta.entity.User;
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

@WebServlet(urlPatterns = ConstantsURLPatterns.ALL_POSTS_BY_USER_SERVLET_URL, name = ConstantsNameServlet.ALL_POSTS_BY_USER_SERVLET_NAME)
public class AllPostsByUserServlet extends HttpServlet {
    private PostService postService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute(ConstantsServiceName.POST_SERVICE);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        List<Post> allByUser = postService.getAllByUser(user);

        req.setAttribute("posts", allByUser);

        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.ALL_POSTS_USER_PATH).forward(req, resp);
    }
}
