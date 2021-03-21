package by.insta.dao;

import by.insta.entity.Post;
import by.insta.Constant;
import by.insta.entity.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PostStorageImpl implements PostStorage {


    private static final List<Post> posts = new ArrayList<>();


    @Override
    public boolean add(Post post){
        int size = posts.size();
        post.setId(++size);
        return posts.add(post);
    }

    @Override
    public Post getById(int id) {
        for (Post post : posts) {
            if (post.getId() == id){
                return post;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public Post getByTitle(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)){
                return post;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public List<Post> getPage(int start){
        if (posts.isEmpty()){
            return posts;
        }
        int end = start;
        for (int i = start; i < posts.size(); i++) {
            if (end == start + Constant.PAGES){
                break;
            }
                end++;
        }
        return posts.subList(start, end);
    }

    @Override
    public int getCountPages(){
        if (posts.isEmpty()){
            return 1;
        }
        return ((posts.size() - 1) / Constant.PAGES) + 1;
    }

    @Override
    public List<Post> getAll(){
        return posts;
    }

    @Override
    public boolean contains(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)){

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Post post) {
        return posts.contains(post);
    }

    @Override
    public boolean contains(int id) {
        for (Post post : posts) {
            if (post.getId() == id){
                return true;
            }
        }
        return false;
    }

}
