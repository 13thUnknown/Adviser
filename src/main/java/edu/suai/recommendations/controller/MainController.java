package edu.suai.recommendations.controller;

import edu.suai.recommendations.model.User;
import edu.suai.recommendations.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String mainPage(Model model) {
        User user = userService.getAuthUser();
        if (user!=null) {
            model.addAttribute("products", user.getRecommendations());
        }
        return "index";
    }

    @GetMapping("/index")
    public String mainPageIndex(Model model) {
        User user = userService.getAuthUser();
        if (user!=null) {
            model.addAttribute("products", user.getRecommendations());
        }
        return "index";
    }
}
