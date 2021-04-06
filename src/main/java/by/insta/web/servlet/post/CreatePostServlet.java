package by.insta.web.servlet.post;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.service.CategoryService;
import by.insta.service.PostService;
import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsPathJSP;
import by.insta.web.constans.ConstantsURLPatterns;
import by.insta.web.servlet.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

@WebServlet(urlPatterns = ConstantsURLPatterns.CREATE_POST_SERVLET_URL, name = ConstantsNameServlet.CREATE_POST_SERVLET_NAME)
public class CreatePostServlet extends HttpServlet {

    private PostService postService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute("postService");
        this.categoryService = (CategoryService) getServletContext().getAttribute("categoryService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", false);
        List<Category> categories = categoryService.getAllCategory();
        req.setAttribute("categories", categories);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.CREATE_POST_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", true);
        List<Category> categories = categoryService.getAllCategory();
        req.setAttribute("categories", categories);

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String urlString = req.getParameter("URL");
        String categoryName = req.getParameter("category");


        User user = (User) req.getSession().getAttribute("user");
        URL url;
        String result;

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            result = "Invalid URL";
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher(ConstantsPathJSP.CREATE_POST_PATH).forward(req, resp);
            return;
        }

        result = validate(title, description, categoryName);
        if (!result.isEmpty()){
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher(ConstantsPathJSP.CREATE_POST_PATH).forward(req, resp);
            return;
        }

        Category category;
        try {
            category = categoryService.getCategoryByName(categoryName);
        }catch (NoSuchElementException e){
            result = "Invalid category";
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher(ConstantsPathJSP.CREATE_POST_PATH).forward(req, resp);
            return;
        }
        Post post = new Post(url, title, description, category, user);
        if (postService.add(post)) {
            result = "Deal!";
        } else {
            result = "Title already in use!";
        }


        req.setAttribute("result", result);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.CREATE_POST_PATH).forward(req, resp);
    }

    private String validate(String title, String description, String category){
        if (Util.isEmpty(title)){
            return "Invalidate title";
        }else if (Util.isEmpty(description)){
            return "Invalidate description";
        }else if (Util.isEmpty(category)) {
            return "Invalidate category";
        } else return "";
    }
}
