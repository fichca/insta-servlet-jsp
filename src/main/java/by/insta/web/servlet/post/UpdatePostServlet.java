package by.insta.web.servlet.post;

import by.insta.dao.LikeStorageImpl;
import by.insta.dao.PostStorageImpl;
import by.insta.entity.Post;
import by.insta.service.CommentService;
import by.insta.service.PostService;
import by.insta.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@WebServlet(urlPatterns = "/updatePost")
public class UpdatePostServlet extends HttpServlet {

    private PostService postService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", false);
        String id = req.getParameter("postId");
        Post post = postService.getById(Integer.parseInt(id));
        req.setAttribute("post", post);
        req.getServletContext().getRequestDispatcher("/pages/post/update_post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("isErrors", true);
        String id = req.getParameter("postId");
        String newDescription = req.getParameter("description");
        String urlString = req.getParameter("URL");

        Post post = postService.getById(Integer.parseInt(id));
        req.setAttribute("post", post);

        URL newUrl;

        try {
            newUrl = new URL(urlString);
        } catch (MalformedURLException e) {
            req.setAttribute("result", "Invalid URL");
            req.getServletContext().getRequestDispatcher("/pages/post/update_post.jsp").forward(req, resp);
            return;
        }

        post.setUrl(newUrl);
        post.setDescription(newDescription);

        req.setAttribute("result", "Deal!");
        req.getServletContext().getRequestDispatcher("/pages/post/update_post.jsp").forward(req, resp);
    }
}
