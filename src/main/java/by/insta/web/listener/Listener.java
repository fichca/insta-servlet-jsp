package by.insta.web.listener;

import by.insta.dao.*;
import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.Role;
import by.insta.entity.User;
import by.insta.service.*;
import lombok.SneakyThrows;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.net.URL;
import java.util.List;

@WebListener
public class Listener implements HttpSessionAttributeListener, HttpSessionListener, ServletContextListener {


    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        CategoryStorage categoryStorage = new CategoryStorageImpl();
        CommentStorage commentStorage = new CommentStorageImpl();
        DialogueStorage dialogueStorage = new DialogueStorageImpl();
        LikeStorage likeStorage = new LikeStorageImpl();
        MessageStorage messageStorage = new MessageStorageImpl();
        PostStorage postStorage = new PostStorageImpl();
        UserStorage userStorage = new UserStorageImpl();

        CategoryService categoryService = new CategoryServiceImpl(categoryStorage);
        CommentService commentService = new CommentServiceImpl(commentStorage);
        DialogService dialogService = new DialogServiceImpl(dialogueStorage, messageStorage);
        LikeService likeService = new LikeServiceImpl(likeStorage);
        MessageService messageService = new MessageServiceImpl(messageStorage);
        PostService postService = new PostServiceImpl(postStorage, likeStorage);
        UserService userService = new UserServiceImpl(userStorage);


        sce.getServletContext().setAttribute("categoryService", categoryService);
        sce.getServletContext().setAttribute("commentService", commentService);
        sce.getServletContext().setAttribute("dialogService", dialogService);
        sce.getServletContext().setAttribute("likeService", likeService);
        sce.getServletContext().setAttribute("messageService", messageService);
        sce.getServletContext().setAttribute("postService", postService);
        sce.getServletContext().setAttribute("userService", userService);


        userStorage.addUser(new User("fichca", "fichca", "1234", Role.USER));
        userStorage.addUser(new User("w580ii@mail.ru", "w580ii@mail.ru", "1234", Role.USER));
        userStorage.addUser(new User("hohol", "hohol", "1234", Role.USER));
        userStorage.addUser(new User("+375257572117", "+375257572117", "1234", Role.USER));
        userStorage.addUser(new User("Kravchenco1974", "Kravchenco1974", "1234", Role.MODERATOR));

        categoryStorage.addCategory(new Category("fichca"));
        categoryStorage.addCategory(new Category("fupanon"));
        categoryStorage.addCategory(new Category("hohol"));
        categoryStorage.addCategory(new Category("baget"));

        User fichcaUser = userStorage.getUserByLogin("fichca");
        User w580iiUser = userStorage.getUserByLogin("w580ii@mail.ru");
        User hoholUser = userStorage.getUserByLogin("hohol");


        Category hohol = categoryStorage.getCategoryByName("hohol");
        Category baget = categoryStorage.getCategoryByName("baget");
        Category fichca = categoryStorage.getCategoryByName("fichca");

        URL url1 = new URL("https://s1.1zoom.ru/big0/52/Love_Sunrises_and_sunsets_Fingers_Hands_Heart_Sun_532758_1280x897.jpg");
        URL url2 = new URL("https://st4.depositphotos.com/1064024/20942/i/600/depositphotos_209420380-stock-photo-digital-illustration-little-cute-unicorn.jpg");
        URL url3 = new URL("https://telecomdom.com/wp-content/uploads/2020/02/kartinki-na-telefon-5-576x1024.jpg");
        URL url4 = new URL("https://st2.depositphotos.com/1064024/10769/i/600/depositphotos_107694484-stock-photo-little-prince-illustration.jpg");
        URL url5 = new URL("https://avatarko.ru/img/kartinka/1/avatarko_anonim.jpg");
        URL url6 = new URL("https://cs8.pikabu.ru/post_img/big/2016/02/04/7/145458292112119207.jpg");

        Post post = new Post(url1, "test1", "test1", hohol, fichcaUser);
        Post post1 = new Post(url6, "test6", "test6", hohol, hoholUser);
        Post post2 = new Post(url2, "test2", "test2", hohol, fichcaUser);
        Post post3 = new Post(url3, "test3", "test3", baget, w580iiUser);
        Post post4 = new Post(url5, "test5", "test5", baget, hoholUser);

        post.setApproved(true);
        post1.setApproved(true);
        post2.setApproved(true);
        post3.setApproved(true);
        post4.setApproved(true);

        postService.add(post);
        postService.add(post2);
        postService.add(post3);
        postService.add(new Post(url4, "test4", "test4", fichca, w580iiUser));
        postService.add(post4);
        postService.add(post1);
        postService.add(new Post(url3, "test7", "test7", fichca, hoholUser));
        List<Category> categories = categoryService.getAllCategory();
        sce.getServletContext().setAttribute("categories", categories);
    }

}
