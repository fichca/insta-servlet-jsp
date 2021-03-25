package by.insta.service;

import by.insta.entity.Category;
import by.insta.entity.Post;

import java.util.List;

public interface CategoryService {

    boolean addCategory(Category category);

    boolean deleteCategoryById(long id);

    boolean deleteCategoryByName(String name);

    Category getCategoryById(long id);

    Category getCategoryByName(String name);


    List<Category> getAllCategory();
}
