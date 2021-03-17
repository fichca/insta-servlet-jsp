package by.insta.web.servlet;

import by.insta.entity.Post;
import by.insta.Constant;
import by.insta.dao.PostStorageImpl;
import by.insta.service.PostService;
import by.insta.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class PostServlet extends HttpServlet {

    PostService postStorage = new PostServiceImpl(new PostStorageImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page = req.getParameter("page");
        if (page == null) {
            page = "1";
        }

        int numberPage = Integer.parseInt(page);
        int start = (numberPage - 1) * Constant.PAGES;
        List<Post> postList = postStorage.getPage(start);
        int countPages = postStorage.getCountPages();


        req.setAttribute("numberPage", numberPage);

        req.setAttribute("countPages", countPages);
        req.setAttribute("posts", postList);
        req.getServletContext().getRequestDispatcher("/pages/post.jsp").forward(req, resp);

    }
}
