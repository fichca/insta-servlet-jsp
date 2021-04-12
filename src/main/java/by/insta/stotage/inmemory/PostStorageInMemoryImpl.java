package by.insta.stotage.inmemory;

import by.insta.stotage.PostStorage;
import by.insta.entity.Category;
import by.insta.entity.Post;
import by.insta.Constant;
import by.insta.entity.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class PostStorageInMemoryImpl implements PostStorage {


    private static final LinkedList<Post> POSTS = new LinkedList<>();



    @Override
    public boolean add(Post post){
        int size = POSTS.size();
        post.setId(++size);
        POSTS.addFirst(post);
        return true;
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
    public Post getById(long id) {
        for (Post post : POSTS) {
            if (post.getId() == id){
                return post;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public Post getByTitle(String title) {
        for (Post post : POSTS) {
            if (post.getTitle().equals(title)){
                return post;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public List<Post> getPage(int start){
        if (POSTS.isEmpty()){
            return POSTS;
        }
        int end = start;
        for (int i = start; i < POSTS.size(); i++) {
            if (end == start + Constant.PAGES){
                break;
            }
                end++;
        }
        return POSTS.subList(start, end);
    }

    @Override
    public int getCountPages(){
        if (POSTS.isEmpty()){
            return 1;
        }
        return ((POSTS.size() - 1) / Constant.PAGES) + 1;
    }

    @Override
    public List<Post> getAll(){
        return POSTS;
    }

    @Override
    public List<Post> getAllApprove() {
        return getAllApprovePost();
    }

    private List<Post> getAllApprovePost(){
        List<Post> postsApprove = new LinkedList<>();
        for (Post post : PostStorageInMemoryImpl.POSTS) {
            if (post.isApproved()){
                postsApprove.add(post);
            }
        }
        return postsApprove;
    }

    @Override
    public List<Post> getAllNotApprove() {
        List<Post> postsNotApprove = new LinkedList<>();
        for (Post post : PostStorageInMemoryImpl.POSTS) {
            if (!post.isApproved()){
                postsNotApprove.add(post);
            }
        }
        return postsNotApprove;
    }

    @Override
    public List<Post> getAllByUser(User user) {
        ArrayList<Post> postsByUser = new ArrayList<>();
        List<Post> allApprove = getAllApprove();

        for (Post post : allApprove) {
            if (post.getUser().equals(user)){
                postsByUser.add(post);
            }
        }
        return postsByUser;
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        ArrayList<Post> postsByCategory = new ArrayList<>();
        List<Post> allApprovePost = getAllApprovePost();
        for (Post post : allApprovePost) {
            if (post.getCategory().equals(category)){
                postsByCategory.add(post);
            }
        }
        return postsByCategory;
    }

    @Override
    public boolean contains(String title) {
        for (Post post : POSTS) {
            if (post.getTitle().equals(title)){

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Post post) {
        return POSTS.contains(post);
    }

    @Override
    public boolean contains(long id) {
        for (Post post : POSTS) {
            if (post.getId() == id){
                return true;
            }
        }
        return false;
    }

}
