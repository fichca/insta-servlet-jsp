package by.insta.web.listener;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.Role;
import by.insta.entity.User;
import by.insta.service.*;
import by.insta.stotage.*;
import by.insta.stotage.db.SubscribersStorageDBImpl;
import by.insta.stotage.db.SubscriptionsStorageDBImpl;
import by.insta.stotage.db.UserStorageDBImpl;
import by.insta.stotage.inmemory.*;
import lombok.SneakyThrows;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.net.URL;
import java.util.List;

import static by.insta.web.constans.ConstantsServiceName.*;

@WebListener
public class Listener implements HttpSessionAttributeListener, HttpSessionListener, ServletContextListener {


    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/insta");

        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setMaxTotal(10);
//        dataSource.setMaxIdle(5);
//        dataSource.setMaxWaitMillis(-1);
//        dataSource.setRemoveAbandonedOnBorrow(true);
//        dataSource.setRemoveAbandonedTimeout(60);
//        dataSource.setLogAbandoned(true);

        CategoryStorage categoryStorage = new CategoryStorageInMemoryImpl();
        CommentStorage commentStorage = new CommentStorageInMemoryImpl();
        DialogueStorage dialogueStorage = new DialogueStorageInMemoryImpl();
        LikeStorage likeStorage = new LikeStorageInMemoryImpl();
        MessageStorage messageStorage = new MessageStorageInMemoryImpl();
        PostStorage postStorage = new PostStorageInMemoryImpl();

        UserStorage userStorage = new UserStorageDBImpl(dataSource);
        SubscribersStorage subscribersStorage = new SubscribersStorageDBImpl(dataSource);
        SubscriptionsStorage subscriptionsStorage = new SubscriptionsStorageDBImpl(dataSource);


        CategoryService categoryService = new CategoryServiceImpl(categoryStorage);
        CommentService commentService = new CommentServiceImpl(commentStorage);
        DialogService dialogService = new DialogServiceImpl(dialogueStorage, messageStorage, userStorage);
        LikeService likeService = new LikeServiceImpl(likeStorage);
        MessageService messageService = new MessageServiceImpl(messageStorage);
        PostService postService = new PostServiceImpl(postStorage, likeStorage, categoryStorage);

        UserService userService = new UserServiceImpl(userStorage, subscribersStorage, subscriptionsStorage);

        sce.getServletContext().setAttribute(CATEGORY_SERVICE, categoryService);
        sce.getServletContext().setAttribute(COMMENT_SERVICE, commentService);
        sce.getServletContext().setAttribute(DIALOG_SERVICE, dialogService);
        sce.getServletContext().setAttribute(LIKE_SERVICE, likeService);
        sce.getServletContext().setAttribute(MASSAGE_SERVICE, messageService);
        sce.getServletContext().setAttribute(POST_SERVICE, postService);
        sce.getServletContext().setAttribute(USER_SERVICE, userService);

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
