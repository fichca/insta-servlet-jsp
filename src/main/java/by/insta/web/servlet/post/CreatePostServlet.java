package by.insta.web.servlet.post;

import by.insta.dao.PostStorageImpl;
import by.insta.entity.Post;
import by.insta.entity.User;
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

@WebServlet(urlPatterns = "/createPost", name = "CreatePostServlet")
public class CreatePostServlet extends HttpServlet {

    PostService postStorage = new PostServiceImpl(new PostStorageImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", false);
        req.getServletContext().getRequestDispatcher("/pages/create_post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("isErrors", true);

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String urlString = req.getParameter("URL");
        User user = (User) req.getSession().getAttribute("user");
        URL url;
        String result;

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            result = "Invalid URL";
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher("/pages/create_post.jsp").forward(req, resp);
            return;
        }


        if (postStorage.add(new Post(url, title, description, user))) {
            result = "Deal!";
        } else {
            result = "Title already in use!";
        }

        req.setAttribute("result", result);
        req.getServletContext().getRequestDispatcher("/pages/create_post.jsp").forward(req, resp);
    }
}
