package by.insta.stotage.db;

import by.insta.Constant;
import by.insta.entity.Like;
import by.insta.entity.User;
import by.insta.stotage.LikeStorage;
import by.insta.stotage.db.mapper.LikeMapper;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static by.insta.stotage.db.Util.closeConnection;

public class LikeStorageDBImpl implements LikeStorage {

    private DataSource dataSource;

    public LikeStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean addLike(Like like) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO likes VALUES(DEFAULT, ?, ?, ?)");
            preparedStatement.setLong(1, like.getPostId());
            preparedStatement.setLong(2, like.getUser().getId());
            preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
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
    public boolean deleteLikeByUser(User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM likes WHERE user_id = ?");
            preparedStatement.setLong(1, user.getId());
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
    public boolean deleteLikeById(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM likes WHERE id = ?");
            preparedStatement.setLong(1, id);
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
    public boolean deleteLikeByPostId(long postId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM likes WHERE post_id = ?");
            preparedStatement.setLong(1, postId);
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
    public Like getLikeById(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select l.id, post_id, u.id, u.name, u.login, u.password, u.role, date from likes l join users u on l.user_id = u.id where l.id = ?");

            preparedStatementUser.setLong(1, id);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return LikeMapper.getLike(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public List<Like> getLikesPage(int start, long postId) {
        List<Like> listLikes = getListLikesByPostId(postId);
        return getListLikesByPage(listLikes, start);
    }

    @Override
    public int getCountLikesPage(long postId) {
        List<Like> listLikes = getListLikesByPostId(postId);
        if (listLikes.isEmpty()){
            return 1;
        }
        return ((listLikes.size() - 1) / Constant.COMMENT_ON_PAGE) + 1;
    }

    @Override
    public List<Like> getAllLikesByPostId(long postId) {
        return getListLikesByPostId(postId);
    }

    @Override
    public List<Like> getAllLikes() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select l.id, post_id, u.id, u.name, u.login, u.password, u.role, date from likes l join users u on l.user_id = u.id");

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return LikeMapper.getLikeList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    public List<Like> getListLikesByPostId(long postId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement("select l.id, post_id, u.id, u.name, u.login, u.password, u.role, date from likes l join users u on l.user_id = u.id where l.post_id = ?");

            preparedStatementUser.setLong(1, postId);

            ResultSet resultSet = preparedStatementUser.executeQuery();
            return LikeMapper.getLikeList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(Like like) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  likes WHERE post_id = ? AND user_id = ?");
            preparedStatement.setLong(1, like.getPostId());
            preparedStatement.setLong(2, like.getUser().getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  likes WHERE id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  likes WHERE user_id = ?");
            preparedStatement.setLong(1, user.getId());
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    private List<Like> getListLikesByPage(List<Like> list, int start){
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
