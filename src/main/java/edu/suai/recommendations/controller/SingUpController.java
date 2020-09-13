package edu.suai.recommendations.controller;

import edu.suai.recommendations.dto.create.UserCreateDto;
import edu.suai.recommendations.model.User;
import edu.suai.recommendations.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class SingUpController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping(value="/sing-up")
    public String greetingForm(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "sing-up";
    }

    @PostMapping(value="/sing-up")
    public String greetingSubmit(@Valid @ModelAttribute UserCreateDto userCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sing-up";
        }
        User user = modelMapper.map(userCreateDto, User.class);
        userService.createUser(user);
        return "index";
    }
}
