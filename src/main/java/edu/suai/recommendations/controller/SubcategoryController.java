package edu.suai.recommendations.controller;

import edu.suai.recommendations.dto.all.SubcategoryDto;
import edu.suai.recommendations.model.Subcategory;
import edu.suai.recommendations.service.SubcategoryService;
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

import static edu.suai.recommendations.common.Constants.SUBCATEGORY;

@Controller
@AllArgsConstructor
@RequestMapping(SUBCATEGORY)
public class SubcategoryController {
    private final SubcategoryService subcategoryService;
    private final ModelMapper modelMapper;

    @GetMapping(value="/add")
    public String subcategoryAddGet(Model model) {
        model.addAttribute("subcategoryDto", new SubcategoryDto());
        return "subcategories/subcategory-add";
    }

    @PostMapping(value="/add")
    public String subcategoryAddPost(@Valid @ModelAttribute SubcategoryDto subcategoryDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "subcategories/subcategory-add";
        }
        Subcategory subcategory = modelMapper.map(subcategoryDto, Subcategory.class);
        subcategoryService.createSubcategory(subcategory);
        return "control";
    }

    @GetMapping(value="/update")
    public String subcategoryUpdateGet(Model model) {
        model.addAttribute("subcategoryDto", new SubcategoryDto());
        return "subcategories/subcategory-update";
    }

    @PostMapping(value="/update")
    public String subcategoryUpdatePost(@Valid @ModelAttribute SubcategoryDto subcategoryDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "subcategories/subcategory-update";
        }
        Subcategory subcategory = modelMapper.map(subcategoryDto, Subcategory.class);
        subcategoryService.updateSubcategory(subcategory);
        return "control";
    }

    @GetMapping(value="/delete")
    public String subcategoryDeleteGet(Model model) {
        model.addAttribute("subcategoryDto", new SubcategoryDto());
        return "subcategories/subcategory-delete";
    }

    @PostMapping(value="/delete")
    public String subcategoryDeletePost(@Valid @ModelAttribute SubcategoryDto subcategoryDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "subcategories/subcategory-delete";
        }
        subcategoryService.deleteSubcategory(subcategoryDto.getTitle());
        return "control";
    }
}