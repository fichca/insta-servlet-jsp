package by.insta.web.servlet.post;

import by.insta.entity.Post;
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
import java.net.MalformedURLException;
import java.net.URL;

@WebServlet(urlPatterns = ConstantsURLPatterns.UPDATE_POST_SERVLET_URL, name = ConstantsNameServlet.UPDATE_POST_SERVLET_NAME)
public class UpdatePostServlet extends HttpServlet {

    private PostService postService;

    @Override
    public void init() throws ServletException {
        this.postService = (PostService) getServletContext().getAttribute(ConstantsServiceName.POST_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isErrors", false);
        String id = req.getParameter("postId");
        Post post = postService.getById(Integer.parseInt(id));
        req.setAttribute("post", post);
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.UPDATE_POST_PATH).forward(req, resp);
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
            req.getServletContext().getRequestDispatcher(ConstantsPathJSP.UPDATE_POST_PATH).forward(req, resp);
            return;
        }

//        post.setUrl(newUrl);
        post.setDescription(newDescription);

        req.setAttribute("result", "Deal!");
        req.getServletContext().getRequestDispatcher(ConstantsPathJSP.UPDATE_POST_PATH).forward(req, resp);
    }
}
