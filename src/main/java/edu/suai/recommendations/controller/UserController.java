package edu.suai.recommendations.controller;

import edu.suai.recommendations.dto.all.CriteriaDto;
import edu.suai.recommendations.dto.all.ProductDto;
import edu.suai.recommendations.dto.create.UserCreateDto;
import edu.suai.recommendations.model.Criteria;
import edu.suai.recommendations.model.Product;
import edu.suai.recommendations.model.User;
import edu.suai.recommendations.service.ProductService;
import edu.suai.recommendations.service.ShopService;
import edu.suai.recommendations.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static edu.suai.recommendations.common.Constants.*;


@Controller
@AllArgsConstructor
@RequestMapping(USER)
public class UserController {

    private final ProductService productService;
    private final ShopService shopService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/me")
    public String meGet(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.getUserByUserName(username);
        model.addAttribute("products", user.getProducts());
        return "user/me";
    }

    @GetMapping(value = PRODUCT+"/search")
    public String userSearchProductGet(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("shops", shopService.getAllShops());
        return "user/product-search";
    }

    @GetMapping(value = OPTION)
    public String userOptionsGet(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.getUserByUserName(username);
        model.addAttribute("options", user.getOptions());
        return "user/options";
    }

    @PostMapping(value=PRODUCT+"/search")
    public String userSearchProductPost(@Valid @ModelAttribute ProductDto productDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REDIRECT+PRODUCT+"/search";
        }
        List<Product> products = productService.getProductsByTitleAndShop(productDto);
        if (products.isEmpty()){
            return REDIRECT+PRODUCT+"/search";
        }
        model.addAttribute("products", products);
        model.addAttribute("productAdd",new Product());
        return "user/product-add";
    }

    @PostMapping(value=PRODUCT+ADD)
    public String userAddProductPost(@ModelAttribute Product product, BindingResult bindingResult, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.getUserByUserName(username);
        productService.addUserProduct(product.getId(),user.getId());
        return REDIRECT+USER+ME;
    }


    @PostMapping("/sing-up")
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewUser(@Valid @RequestBody UserCreateDto userCreateDto, BindingResult bindingResult, Model model) {
        User user = modelMapper.map(userCreateDto, User.class);
        if (bindingResult.hasErrors()) {
            return "sing-up";
        }
        if (userService.createUser(user)) {
            return "sing-up";
        }
        return "redirect:/";
    }
}

