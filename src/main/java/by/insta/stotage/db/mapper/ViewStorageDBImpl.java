package by.insta.stotage.db.mapper;

import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.entity.View;
import by.insta.stotage.ViewStorage;

import javax.sql.DataSource;

public class ViewStorageDBImpl implements ViewStorage {
    private DataSource dataSource;

    public ViewStorageDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addView(View view) {

    }

    @Override
    public void addUserViewByPostId(long postId, User user) {

    }

    @Override
    public void delete(View view) {

    }

    @Override
    public void delete(long postId) {

    }

    @Override
    public View getViewByPostId(int postId) {
        return null;
    }


    @Override
    public View getViewById(long id) {
        return null;
    }

    @Override
    public View getAllViews() {
        return null;
    }

    @Override
    public boolean contains(long id) {
        return false;
    }

    @Override
    public boolean contains(View view) {
        return false;
    }

    @Override
    public boolean contains(Post post) {
        return false;
    }


}
