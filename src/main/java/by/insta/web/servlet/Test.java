package by.insta.web.servlet;

import by.insta.dao.CategoryStorage;
import by.insta.dao.CategoryStorageImpl;
import by.insta.dao.UserStorage;
import by.insta.dao.UserStorageImpl;
import by.insta.entity.Category;
import by.insta.entity.User;
import by.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/test")
public class Test extends HttpServlet {

    private final UserStorage userStorage = new UserStorageImpl();
    private final CategoryStorage categoryStorage = new CategoryStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userStorage.addUser(new User("fupanon", "fichca", "1234"));
        userStorage.addUser(new User("fupanon", "w580ii@mail.ru", "1234"));
        userStorage.addUser(new User("fupanon", "hohol", "1234"));
        userStorage.addUser(new User("fupanon", "+375257572117", "1234"));
        userStorage.addUser(new User("fupanon", "Kravchenco1974", "1234"));

        categoryStorage.addCategory(new Category("fichca"));
        categoryStorage.addCategory(new Category("fupanon"));
        categoryStorage.addCategory(new Category("hohol"));
        categoryStorage.addCategory(new Category("baget"));

        List<User> allUsers = userStorage.getAllUsers();
        List<Category> allCategory = categoryStorage.getAllCategory();
        resp.sendRedirect("/auth");
    }
}
