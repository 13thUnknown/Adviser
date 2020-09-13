package edu.suai.recommendations.controller;

import edu.suai.recommendations.dto.all.CategoryDto;
import edu.suai.recommendations.model.Category;
import edu.suai.recommendations.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static edu.suai.recommendations.common.Constants.*;

@Controller
@AllArgsConstructor
@RequestMapping(CATEGORY)
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping(value="/add")
    public String categoryAddGet(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "categories/category-add";
    }

    @PostMapping(value="/add")
    public String categoryAddPost(@Valid @ModelAttribute CategoryDto categoryDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/category-add";
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryService.createCategory(category);
        return REDIRECT+CONTROL;
    }

    @GetMapping(value="/update")
    public String categoryUpdateGet(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "categories/category-update";
    }

    @PostMapping(value="/update")
    public String categoryUpdatePost(@Valid @ModelAttribute CategoryDto categoryDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/category-update";
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryService.updateCategory(category);
        return "control";
    }

    @GetMapping(value="/delete")
    public String categoryDeleteGet(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "categories/category-delete";
    }

    @PostMapping(value="/delete")
    public String categoryDeletePost(@Valid @ModelAttribute CategoryDto categoryDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/category-delete";
        }
        categoryService.deleteCategory(categoryDto.getTitle());
        return REDIRECT+CONTROL;
    }
}
