package edu.suai.recommendations.service;

import edu.suai.recommendations.exception.NotFoundException;
import edu.suai.recommendations.model.Category;
import edu.suai.recommendations.model.Subcategory;
import edu.suai.recommendations.repository.SubcategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;

    @Transactional(readOnly = true)
    public Subcategory getSubcategoryByTitle(String title) {
        return subcategoryRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Subcategory.class));
    }

    @Transactional(readOnly = true)
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Transactional
    public boolean createSubcategory(Subcategory subcategory){
        subcategoryRepository.save(subcategory);
        return true;
}

    @Transactional
    public boolean updateSubcategory(Subcategory subcategory){
        Subcategory object = subcategoryRepository.findByTitle(subcategory.getTitle()).orElseThrow(() -> new NotFoundException(Subcategory.class));
        object.setDescription(subcategory.getDescription());
        return true;
    }

    @Transactional
    public boolean deleteSubcategory(String title){
        Subcategory object = subcategoryRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Subcategory.class));
        subcategoryRepository.delete(object);
        return true;
    }
}
