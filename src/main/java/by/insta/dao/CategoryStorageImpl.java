package by.insta.dao;

import by.insta.entity.Category;
import by.insta.entity.Like;
import by.insta.entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CategoryStorageImpl implements CategoryStorage {

    private static final List<Category> categories = new ArrayList<>();


    @Override
    public boolean addCategory(Category category) {
        int size = categories.size();
        category.setId(++size);
        return categories.add(category);
    }

    @Override
    public boolean deleteCategoryById(long id) {
        for (Category category : categories) {
            if (category.getId() == id){
                categories.remove(category);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)){
                categories.remove(category);
                return true;
            }
        }
        return false;
    }

    @Override
    public Category getCategoryById(long id) {
        for (Category category : categories) {
            if (category.getId() == id){
                return category;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)){
                return category;
            }
        } throw new NoSuchElementException();
    }

    @Override
    public List<Category> getAllCategory() {
        return categories;
    }

    @Override
    public boolean contains(Category category) {
        for (Category category1 : categories) {
            if (category1.equals(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(long id) {
        for (Category category : categories) {
            if (category.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
