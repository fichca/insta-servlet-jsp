package by.insta.service;

import by.insta.dao.CategoryStorage;
import by.insta.entity.Category;
import by.insta.entity.Post;

import java.util.List;
import java.util.NoSuchElementException;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryStorage categoryStorage;

    public CategoryServiceImpl(CategoryStorage categoryStorage) {
        this.categoryStorage = categoryStorage;
    }

    @Override
    public boolean addCategory(Category category) {
        if (!categoryStorage.contains(category)){
            return categoryStorage.addCategory(category);
        }throw new NoSuchElementException();
    }

    @Override
    public boolean deleteCategoryById(long id) {
        if (categoryStorage.contains(id)){
            return categoryStorage.deleteCategoryById(id);
        }
        return false;
    }

    @Override
    public boolean deleteCategoryByName(String name) {
        if (categoryStorage.contains(name)){
            return categoryStorage.deleteCategoryByName(name);
        }
        return false;
    }

    @Override
    public Category getCategoryById(long id) {
        if (categoryStorage.contains(id)){
            return categoryStorage.getCategoryById(id);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Category getCategoryByName(String name) {
        if (categoryStorage.contains(name)){
            return categoryStorage.getCategoryByName(name);
        } throw new NoSuchElementException();
    }



    @Override
    public List<Category> getAllCategory() {
        return categoryStorage.getAllCategory();
    }
}
