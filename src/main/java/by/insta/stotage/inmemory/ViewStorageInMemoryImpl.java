package by.insta.stotage.inmemory;

import by.insta.entity.Post;
import by.insta.entity.User;
import by.insta.entity.View;
import by.insta.stotage.ViewStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ViewStorageInMemoryImpl implements ViewStorage {

    private static final List<View> views = new ArrayList<>();

    @Override
    public void addView(View viewIn) {
        int size = views.size();
        viewIn.setId(++size);
        views.add(viewIn);
    }

    @Override
    public void addUserViewByPostId(long postId, User user) {

        for (View view : views) {
            if (view.getPostId() == postId){
                view.getUserViews().add(user);
                return;
            }
        }
    }

    @Override
    public void delete(View view) {

    }


    @Override
    public void delete(long id) {

    }

    @Override
    public View getViewByPostId(int postId) {
        for (View view : views) {
            if (view.getPostId() == postId){
                return view;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public View getViewById(long id) {
        for (View view : views) {
            if (view.getId() == id){
                return view;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public View getAllViews() {
        return null;
    }

    @Override
    public boolean contains(long id) {
        for (View view : views) {
            if (view.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(View view) {
        for (View view1 : views) {
            if (view1.equals(view)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Post post) {
        for (View view : views) {
            if (view.getPostId() == post.getId()){
                return true;
            }
        }
        return false;
    }


}
