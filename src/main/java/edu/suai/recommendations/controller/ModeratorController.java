package edu.suai.recommendations.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static edu.suai.recommendations.common.Constants.MODER;

@Controller
@AllArgsConstructor
@RequestMapping(MODER)
public class ModeratorController {

    @GetMapping
    public String moderMenu(Model model) {
        return "control";
    }
}
