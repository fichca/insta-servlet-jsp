package by.insta.web.servlet.post;

import by.insta.Constant;
import by.insta.dao.CategoryStorageImpl;
import by.insta.dao.LikeStorageImpl;
import by.insta.dao.PostStorageImpl;
import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.service.CategoryService;
import by.insta.service.CategoryServiceImpl;
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

@WebServlet(urlPatterns = "/posts/bySubscriptions", name = "AllPostsFeedBySubscriptions")
public class AllPostsFeedBySubscriptions extends HttpServlet {

    private PostService postService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute("postService");
        this.categoryService = (CategoryService) getServletContext().getAttribute("categoryService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> categories = categoryService.getAllCategory();
        req.setAttribute("categories", categories);

        String page = req.getParameter("page");

        if (page == null) {
            page = "1";
        }
        User user = (User) req.getSession().getAttribute("user");

        List<Post> posts = postService.getAllBySubscriptions(user);


        int numberPage = Integer.parseInt(page);
        int start = (numberPage - 1) * Constant.PAGES;
        List<Post> postList = Util.getPage(start, posts);
        int countPages = Util.getCountPages(posts);


        req.setAttribute("numberPage", numberPage);
        req.setAttribute("countPages", countPages);
        req.setAttribute("posts", postList);

        req.getServletContext().getRequestDispatcher("/pages/post/posts_feed_by_subscriptions.jsp").forward(req, resp);

    }
}
