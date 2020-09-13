package edu.suai.recommendations.controller;

import edu.suai.recommendations.dto.create.OptionCreateDto;
import edu.suai.recommendations.model.Option;
import edu.suai.recommendations.service.CriteriaService;
import edu.suai.recommendations.service.OptionService;
import edu.suai.recommendations.service.ProductService;
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
@RequestMapping(OPTION)
public class OptionController {
    private final ProductService productService;
    private final OptionService optionService;
    private final CriteriaService criteriaService;
    private final ModelMapper modelMapper;

    @GetMapping(value="/add/{id}")
    public String optionToProductAddGet(Model model, @PathVariable("id") long id) {
        OptionCreateDto optionCreateDto = new OptionCreateDto();
        optionCreateDto.setProductId(id);
        model.addAttribute("criterias", criteriaService.findAllCriteria());
        model.addAttribute("optionCreateDto", optionCreateDto);
        model.addAttribute("options",productService.getOptionsByProductId(id));
        return "options/option-add";
    }


    @PostMapping(value="/add")
    public String optionToProductAddPost(@Valid @ModelAttribute OptionCreateDto optionCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/add/{productId}";
        }
        optionService.createOption(modelMapper.map(optionCreateDto, Option.class),optionCreateDto.getProductId());
        return REDIRECT + OPTION + ADD + "/" + optionCreateDto.getProductId();
    }
}
