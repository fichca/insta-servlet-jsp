package by.insta.stotage.db;

import by.insta.Constant;
import by.insta.entity.Comment;
import by.insta.entity.Role;
import by.insta.entity.User;
import by.insta.stotage.CommentStorage;
import by.insta.stotage.db.mapper.CommentMapper;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static by.insta.stotage.db.Util.closeConnection;

public class CommentStorageDBImpl implements CommentStorage {
    private DataSource dataSource;

    public CommentStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    public static void main(String[] args) {
//        BasicDataSource dataSource = new BasicDataSource();
//
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/insta");
//
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("root");
//        dataSource.setDriverClassName("org.postgresql.Driver");
//
//
//        CommentStorageDBImpl commentStorageDB = new CommentStorageDBImpl(dataSource);
//
//        Comment comment1 = new Comment(2, 1, "test4", new User(7, "test", "test", "test", Role.USER), new Date());
//        Comment comment = new Comment(1, "test4", new User(7, "test", "test", "test", Role.USER));
//        System.out.println(commentStorageDB.contains(2));
//
//    }

    @Override
    public boolean add(Comment comment) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comments VALUES(DEFAULT, ?, ?, ?, ?)");
            preparedStatement.setLong(1, comment.getPostId());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setLong(3, comment.getUser().getId());
            preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            Util.closeConnection(connection);
        }
        return true;
    }

    @Override
    public Comment getById(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select c.id, post_id, comment, u.id, name, login, password, role, date from  comments c  join users u on c.user_id = u.id where c.id = ?");

            preparedStatementUser.setLong(1, id);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return CommentMapper.getComment(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Comment> getCommentsPage(int start, long postId) {
        List<Comment> allCommentsByPostId = getListCommentByPostId(postId);
        return getListCommentByPage(allCommentsByPostId,start);
    }

    @Override
    public int getCountCommentsPage(long postId) {
        List<Comment> allCommentsByPostId = getListCommentByPostId(postId);
        if (allCommentsByPostId.isEmpty()){
            return 1;
        }
        return ((allCommentsByPostId.size() - 1) / Constant.COMMENT_ON_PAGE) + 1;
    }

    @Override
    public List<Comment> getAllCommentsByPostId(long postId) {
        return getListCommentByPostId(postId);
    }

    public List<Comment> getListCommentByPostId(long postId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select c.id, post_id, comment, u.id, name, login,password, role, date from  comments c  join users u on c.user_id = u.id where c.post_id = ?");
            preparedStatementUser.setLong(1, postId);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return CommentMapper.getCommentList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Comment> getAll() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select c.id, post_id, comment, u.id, name, login,password, role, date from  comments c  join users u on c.user_id = u.id");

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return CommentMapper.getCommentList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(Comment comment) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  comments WHERE id = ? AND post_id = ? AND comment = ? AND user_id = ?");
            preparedStatement.setLong(1, comment.getId());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.setString(3, comment.getComment());
            preparedStatement.setLong(4, comment.getUser().getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  comments WHERE id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    private List<Comment> getListCommentByPage(List<Comment> list, int start){

        if (list.isEmpty()){
            return list;
        }
        int end = start;
        for (int i = start; i < list.size(); i++) {
            if (end == start + Constant.COMMENT_ON_PAGE){
                break;
            }
            end++;
        }
        return list.subList(start, end);
    }
}
