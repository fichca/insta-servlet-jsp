package by.insta.stotage;

import by.insta.entity.Category;

import java.util.List;

public interface CategoryStorage {

    boolean addCategory(Category category);

    boolean deleteCategoryById(long id);

    boolean deleteCategoryByName(String name);

    Category getCategoryById(long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategory();

    boolean contains(Category category);

    boolean contains(long id);

    boolean contains(String name);
}
