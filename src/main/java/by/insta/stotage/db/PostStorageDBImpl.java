package by.insta.stotage.db;

import by.insta.Constant;
import by.insta.entity.Category;
import by.insta.entity.Comment;
import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.stotage.PostStorage;
import by.insta.stotage.db.mapper.ImgMapper;
import by.insta.stotage.db.mapper.PostMapper;
import by.insta.stotage.db.mapper.UserMapper;
import lombok.var;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static by.insta.stotage.db.Util.closeConnection;

public class PostStorageDBImpl implements PostStorage {
    private DataSource dataSource;

    public PostStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws IOException {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/insta");

        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("org.postgresql.Driver");
        PostStorageDBImpl postStorageDB = new PostStorageDBImpl(dataSource);

//        System.out.println(postStorageDB.getById(1));

        InputStream inputStream = new FileInputStream("D:\\123.jpg");

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();

        String s = encoder.encodeToString(buffer);
        byte[] encode = encoder.encode(buffer);

        byte[] bytes = s.getBytes();

        byte[] decode = decoder.decode(bytes);
        String s3 = encoder.encodeToString(decode);


        String s1 = encoder.encodeToString(bytes);
        String s2 = Arrays.toString(bytes);
        System.out.println("sss");

//        List<String> list = new ArrayList<>();
//        list.add(s);
//        boolean add = postStorageDB.add(new Post("test", "test", new Category(1, "sport"), new User(1, "test", "test", "tse", Role.USER), list));
//        System.out.println(add);
        System.out.println(postStorageDB.getAll());
    }


    @Override
    public boolean add(Post post) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO posts VALUES(DEFAULT, ?, ?, ?, ?, ?) returning id");

            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getDescription());
            preparedStatement.setLong(3, post.getCategory().getId());
            preparedStatement.setLong(4, post.getUser().getId());
            preparedStatement.setBoolean(5, post.isApproved());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int postId = resultSet.getInt(1);

//            List<byte[]> listImgByte = getListImgByte(post.getImgStringBase64());
//            addImages(listImgByte, postId);
            addImages(post.getImgStringBase64(), postId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
        return true;
    }

//    private void addImages(List<byte[]> listImgByte, int postId) {
    private void addImages(List<String> listImgByte, int postId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            for (String s : listImgByte) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO images VALUES(DEFAULT, ?, ?)");

                byte[] bytes = s.getBytes();
                preparedStatement.setInt(1, postId);
                preparedStatement.setBytes(2, bytes);
                preparedStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    private List<byte[]> getListImgByte(List<String> img) {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        List<byte[]> listImgByte = new ArrayList<>();
        for (String s : img) {
            byte[] decode = decoder.decode(s);
            byte[] encode = encoder.encode(decode);
            listImgByte.add(encode);
        }
        return listImgByte;
    }

    @Override
    public void addUserViewByPost(Post post, User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO views VALUES(?, ?)");

            preparedStatement.setLong(1, post.getId());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean delete(Post post) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public void approvePost(Post post) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update posts set approved = ? where id = ?");

            preparedStatement.setBoolean(1, true);
            preparedStatement.setLong(2, post.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void rejectPost(Post post) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update posts set approved = ? where id = ?");

            preparedStatement.setBoolean(1, false);
            preparedStatement.setLong(2, post.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }


    @Override
    public Post getById(long id) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select p.id, p.title, p.description, c.id, c.name, u.id,u.name, u.login, u.password, u.role, p.approved  from posts p join categories c on p.category_id = c.id join users u on p.user_id = u.id where p.id = ?");

            preparedStatementUser.setLong(1, id);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            Post post = PostMapper.getPost(resultSet);
            List<String> imgStringBase64 = getImgStringBase64(id);
            Set<User> views = getViews(id);
            post.setImgStringBase64(imgStringBase64);

            post.setViews(views);
            return post;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Post getByTitle(String title) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select p.id, p.title, p.description, c.id, c.name, u.id,u.name, u.login, u.password, u.role, p.approved  from posts p join categories c on p.category_id = c.id join users u on p.user_id = u.id where p.title = ?");

            preparedStatementUser.setString(1, title);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            Post post = PostMapper.getPost(resultSet);
            List<String> imgStringBase64 = getImgStringBase64(post.getId());
            Set<User> views = getViews(post.getId());
            post.setImgStringBase64(imgStringBase64);

            post.setViews(views);
            return post;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Post> getPage(int start) {
        List<Post> all = getAll();
        if (all.isEmpty()) {
            return all;
        }
        int end = start;
        for (int i = start; i < all.size(); i++) {
            if (end == start + Constant.PAGES) {
                break;
            }
            end++;
        }
        return all.subList(start, end);
    }

    @Override
    public int getCountPages() {

        List<Post> all = getAll();
        if (all.isEmpty()) {
            return 1;
        }
        return ((all.size() - 1) / Constant.PAGES) + 1;
    }

    @Override
    public List<Post> getAll() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select p.id, p.title, p.description, c.id, c.name, u.id,u.name, u.login, u.password, u.role, p.approved  from posts p join categories c on p.category_id = c.id join users u on p.user_id = u.id");


            ResultSet resultSet = preparedStatementUser.executeQuery();
            List<Post> postList = PostMapper.getPostList(resultSet);
            for (Post post : postList) {
                List<String> imgStringBase64 = getImgStringBase64(post.getId());
                Set<User> views = getViews(post.getId());
                post.setImgStringBase64(imgStringBase64);
                post.setViews(views);
            }
            return postList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Post> getAllApprove() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select p.id, p.title, p.description, c.id, c.name, u.id,u.name, u.login, u.password, u.role, p.approved  from posts p join categories c on p.category_id = c.id join users u on p.user_id = u.id where p.approved = ?");

            preparedStatementUser.setBoolean(1, true);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            List<Post> postList = PostMapper.getPostList(resultSet);
            for (Post post : postList) {
                List<String> imgStringBase64 = getImgStringBase64(post.getId());
                Set<User> views = getViews(post.getId());
                post.setImgStringBase64(imgStringBase64);
                post.setViews(views);
            }
            return postList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Post> getAllNotApprove() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select p.id, p.title, p.description, c.id, c.name, u.id,u.name, u.login, u.password, u.role, p.approved  from posts p join categories c on p.category_id = c.id join users u on p.user_id = u.id where p.approved = ?");

            preparedStatementUser.setBoolean(1, false);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            List<Post> postList = PostMapper.getPostList(resultSet);
            for (Post post : postList) {
                List<String> imgStringBase64 = getImgStringBase64(post.getId());
                Set<User> views = getViews(post.getId());
                post.setImgStringBase64(imgStringBase64);
                post.setViews(views);
            }
            return postList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Post> getAllByUser(User user) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select p.id, p.title, p.description, c.id, c.name, u.id,u.name, u.login, u.password, u.role, p.approved  from posts p join categories c on p.category_id = c.id join users u on p.user_id = u.id where p.user_id = ?");

            preparedStatementUser.setLong(1, user.getId());

            ResultSet resultSet = preparedStatementUser.executeQuery();
            List<Post> postList = PostMapper.getPostList(resultSet);
            for (Post post : postList) {
                List<String> imgStringBase64 = getImgStringBase64(post.getId());
                Set<User> views = getViews(post.getId());
                post.setImgStringBase64(imgStringBase64);
                post.setViews(views);
            }
            return postList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select p.id, p.title, p.description, c.id, c.name, u.id,u.name, u.login, u.password, u.role, p.approved  from posts p join categories c on p.category_id = c.id join users u on p.user_id = u.id where p.category_id = ?");

            preparedStatementUser.setLong(1, category.getId());

            ResultSet resultSet = preparedStatementUser.executeQuery();
            List<Post> postList = PostMapper.getPostList(resultSet);
            for (Post post : postList) {
                List<String> imgStringBase64 = getImgStringBase64(post.getId());
                Set<User> views = getViews(post.getId());
                post.setImgStringBase64(imgStringBase64);
                post.setViews(views);
            }
            return postList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(String title) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  posts WHERE title = ?");
            preparedStatement.setString(1, title);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean contains(Post post) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  posts WHERE title = ?");
            preparedStatement.setString(1, post.getTitle());
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean contains(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  posts WHERE id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    private Set<User> getViews(long postId){
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select u.id, u.name, u.login, u.password, u.role from views v join users u on v.user_id = u.id where v.post_id = ?");

            preparedStatementUser.setLong(1, postId);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            List<User> userList = UserMapper.getUserList(resultSet);
            return new HashSet<>(userList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }


    private List<String> getImgStringBase64(long postId){
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select img from images where post_id = ?");

            preparedStatementUser.setLong(1, postId);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return ImgMapper.getImgList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }
}
