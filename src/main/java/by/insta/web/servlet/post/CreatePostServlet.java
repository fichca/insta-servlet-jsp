package by.insta.web.servlet.post;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.service.CategoryService;
import by.insta.service.PostService;
import by.insta.web.constans.ConstantsNameServlet;
import by.insta.web.constans.ConstantsPathJSP;
import by.insta.web.constans.ConstantsServiceName;
import by.insta.web.constans.ConstantsURLPatterns;
import by.insta.web.servlet.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@WebServlet(urlPatterns = ConstantsURLPatterns.CREATE_POST_SERVLET_URL, name = ConstantsNameServlet.CREATE_POST_SERVLET_NAME)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)


public class CreatePostServlet extends HttpServlet {

    public static void main(String[] args) {
        String s = "value = " + 2 + 2;
        System.out.println(s);

    }

    private PostService postService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute(ConstantsServiceName.POST_SERVICE);
        this.categoryService = (CategoryService) getServletContext().getAttribute(ConstantsServiceName.CATEGORY_SERVICE);
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
        String categoryId = req.getParameter("category");

        User user = (User) req.getSession().getAttribute("user");
        String result;

        Collection<Part> parts = req.getParts();
        List<String> listStringBase64Img = postService.getListImg(parts);

        result = validate(title, description, listStringBase64Img);
        if (!result.isEmpty()) {
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher(ConstantsPathJSP.CREATE_POST_PATH).forward(req, resp);
            return;
        }

        Category category;
        try {
            category = categoryService.getCategoryById(Integer.parseInt(categoryId));
        } catch (
                NoSuchElementException e) {
            result = "Invalid category";
            req.setAttribute("result", result);
            req.getServletContext().getRequestDispatcher(ConstantsPathJSP.CREATE_POST_PATH).forward(req, resp);
            return;
        }

        Post post = new Post(title, description, category, user, listStringBase64Img);
        if (postService.add(post)) {
            result = "Deal!";
        } else {
            result = "Title already in use!";
        }


        req.setAttribute("result", result);
        req.getServletContext().

                getRequestDispatcher(ConstantsPathJSP.CREATE_POST_PATH).

                forward(req, resp);

    }

    private String validate(String title, String description, List<String> listImg) {
        if (Util.isEmpty(title)) {
            return "Invalidate title";
        } else if (Util.isEmpty(description)) {
            return "Invalidate description";
        } else if (listImg.size() == 0) {
            return "Download img";
        } else return "";
    }
}
