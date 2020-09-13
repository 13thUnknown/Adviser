package edu.suai.recommendations.controller;

import edu.suai.recommendations.dto.all.CriteriaDto;
import edu.suai.recommendations.dto.all.ProductDto;
import edu.suai.recommendations.dto.create.OptionCreateDto;
import edu.suai.recommendations.model.Criteria;
import edu.suai.recommendations.model.Product;
import edu.suai.recommendations.service.CategoryService;
import edu.suai.recommendations.service.ProductService;
import edu.suai.recommendations.service.ShopService;
import edu.suai.recommendations.service.SubcategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static edu.suai.recommendations.common.Constants.*;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ShopService shopService;
    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;
    private final ModelMapper modelMapper;

    @GetMapping(value="/add")
    public String productAddGet(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("shops", shopService.getAllShops());
        model.addAttribute("productDto", new ProductDto());
        return "products/product-add";
    }

    @GetMapping(value="/done/{productId}")
    public String productDoneGet(Model model, @PathVariable("productId") long productId) {
        productService.doneCreationProduct(productId);
        return REDIRECT+CONTROL;
    }

    @PostMapping(value="/add")
    public String productAddPost(@Valid @ModelAttribute ProductDto productDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/products/product-add";
        }
        System.out.println(productDto.toString());
        Product product = productService.createProduct(modelMapper.map(productDto,Product.class));
        return REDIRECT + OPTION + ADD + "/" + product.getId();
    }
}
