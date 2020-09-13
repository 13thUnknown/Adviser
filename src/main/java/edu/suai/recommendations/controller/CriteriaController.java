package edu.suai.recommendations.controller;

import edu.suai.recommendations.dto.all.CriteriaDto;
import edu.suai.recommendations.model.Criteria;
import edu.suai.recommendations.service.CriteriaService;
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

import static edu.suai.recommendations.common.Constants.CRITERIA;

@Controller
@AllArgsConstructor
@RequestMapping(CRITERIA)
public class CriteriaController {
    private final CriteriaService criteriaService;
    private final ModelMapper modelMapper;

    @GetMapping(value="/add")
    public String criteriaAddGet(Model model) {
        model.addAttribute("criteriaDto", new CriteriaDto());
        return "criteria/criteria-add";
    }

    @PostMapping(value="/add")
    public String criteriaAddPost(@Valid @ModelAttribute CriteriaDto criteriaDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "criteria/criteria-add";
        }
        Criteria criteria = modelMapper.map(criteriaDto, Criteria.class);
        criteriaService.createCriteria(criteria);
        return "redirect:/control";
    }


    @GetMapping(value="/delete")
    public String criteriaDeleteGet(Model model) {
        model.addAttribute("criteriaDto", new CriteriaDto());
        return "criteria/criteria-delete";
    }

    @PostMapping(value="/delete")
    public String criteriaDeletePost(@Valid @ModelAttribute CriteriaDto criteriaDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "criteria/criteria-delete";
        }
        criteriaService.deleteCriteria(criteriaDto.getTitle());
        return "control";
    }
}
