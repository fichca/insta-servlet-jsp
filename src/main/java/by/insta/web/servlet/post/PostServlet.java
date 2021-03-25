package by.insta.web.servlet.post;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.Constant;
import by.insta.dao.PostStorageImpl;
import by.insta.service.PostService;
import by.insta.service.PostServiceImpl;
import by.insta.web.servlet.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class PostServlet extends HttpServlet {

    PostService postService = new PostServiceImpl(new PostStorageImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryString = req.getParameter("category");

        String page = req.getParameter("page");
        if (page == null) {
            page = "1";
        }

        List<Post> posts;

        if (categoryString != null){
            Category category = new Category(categoryString);
            posts = postService.getAllByCategory(category);
            req.setAttribute("category", category);
        } else {
            posts = postService.getAll();
        }

        int numberPage = Integer.parseInt(page);
        int start = (numberPage - 1) * Constant.PAGES;

        List<Post> postList = Util.getPage(start, posts);
        int countPages = Util.getCountPages(posts);


        req.setAttribute("numberPage", numberPage);

        req.setAttribute("countPages", countPages);
        req.setAttribute("posts", postList);
        req.getServletContext().getRequestDispatcher("/pages/post/post.jsp").forward(req, resp);

    }
}
