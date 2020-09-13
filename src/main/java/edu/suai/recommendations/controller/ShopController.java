package edu.suai.recommendations.controller;

import edu.suai.recommendations.dto.create.ShopCreateDto;
import edu.suai.recommendations.dto.create.UserCreateDto;
import edu.suai.recommendations.dto.delete.ShopDeleteDto;
import edu.suai.recommendations.dto.update.ShopUpdateDto;
import edu.suai.recommendations.model.Shop;
import edu.suai.recommendations.model.User;
import edu.suai.recommendations.service.ShopService;
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

import static edu.suai.recommendations.common.Constants.SHOP;

@Controller
@AllArgsConstructor
@RequestMapping(SHOP)
public class ShopController {

    private final ShopService shopService;
    private final ModelMapper modelMapper;

    @GetMapping(value="/add")
    public String shopAddGet(Model model) {
        model.addAttribute("shopCreateDto", new ShopCreateDto());
        return "shops/shop-add";
    }

    @PostMapping(value="/add")
    public String shopAddPost(@Valid @ModelAttribute ShopCreateDto shopCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "shops/shop-add";
        }
        Shop shop = modelMapper.map(shopCreateDto, Shop.class);
        shopService.createShop(shop);
        return "control";
    }

    @GetMapping(value="/update")
    public String shopUpdateGet(Model model) {
        model.addAttribute("shopUpdateDto", new ShopUpdateDto());
        return "shops/shop-update";
    }

    @PostMapping(value="/update")
    public String shopUpdatePost(@Valid @ModelAttribute ShopUpdateDto shopUpdateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "shops/shop-update";
        }
        Shop shop = modelMapper.map(shopUpdateDto, Shop.class);
        shopService.updateShop(shop);
        return "control";
    }

    @GetMapping(value="/delete")
    public String shopDeleteGet(Model model) {
        model.addAttribute("shopDeleteDto", new ShopDeleteDto());
        return "shops/shop-delete";
    }

    @PostMapping(value="/delete")
    public String shopDeletePost(@Valid @ModelAttribute ShopDeleteDto shopDeleteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "shops/shop-delete";
        }
        shopService.deleteShop(shopDeleteDto.getTitle());
        return "control";
    }
}
