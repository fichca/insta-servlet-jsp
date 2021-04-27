package by.insta.web.listener;

import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.service.*;
import by.insta.stotage.*;
import by.insta.stotage.db.*;
import by.insta.stotage.db.mapper.ViewStorageDBImpl;
import by.insta.stotage.inmemory.*;
import lombok.SneakyThrows;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.net.URL;
import java.util.List;

import static by.insta.web.constans.ConstantsServiceName.*;

@WebListener
public class Listener implements HttpSessionAttributeListener, HttpSessionListener, ServletContextListener {



    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/insta");

        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("org.postgresql.Driver");


        CategoryStorage categoryStorage = new CategoryStorageDBImpl(dataSource);
        CommentStorage commentStorage = new CommentStorageDBImpl(dataSource);
        DialogueStorage dialogueStorage = new DialogueStorageDBImpl(dataSource);
        LikeStorage likeStorage = new LikeStorageDBImpl(dataSource);
        MessageStorage messageStorage = new MessageStorageDBImpl(dataSource);

        PostStorage postStorage = new PostStorageDBImpl(dataSource);

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
    }

}
