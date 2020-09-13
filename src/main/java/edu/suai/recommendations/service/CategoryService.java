package edu.suai.recommendations.service;

import edu.suai.recommendations.exception.NotFoundException;
import edu.suai.recommendations.model.Category;
import edu.suai.recommendations.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Category getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Category.class));
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public boolean createCategory(Category category){
        categoryRepository.save(category);
        return true;
    }

    @Transactional
    public boolean updateCategory(Category category){
        Category object = categoryRepository.findByTitle(category.getTitle()).orElseThrow(() -> new NotFoundException(Category.class));
        object.setDescription(category.getDescription());
        return true;
    }

    @Transactional
    public boolean deleteCategory(String title){
        Category object = categoryRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Category.class));
        categoryRepository.delete(object);
        return true;
    }
}
