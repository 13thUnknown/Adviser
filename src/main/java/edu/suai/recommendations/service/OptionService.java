package edu.suai.recommendations.service;

import edu.suai.recommendations.model.Option;
import edu.suai.recommendations.model.Product;
import edu.suai.recommendations.repository.OptionRepository;
import edu.suai.recommendations.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    private final ProductRepository productRepository;


    @Transactional
    public boolean createOption(Option option, Long productId){
        option = optionRepository.save(option);
        Product product = productRepository.findById(productId).get();
        Set<Option> options = product.getOptions();
        options.add(option);
        return true;
    }
    @Transactional
    public Option createOptionWithoutProduct(Option option){
        return optionRepository.save(option);

    }


//    @Transactional
//    public boolean updateOption(Option option){
//        Option object = optionRepository.findByTitle(option.getTitle()).orElseThrow(() -> new NotFoundException(Option.class));
//        object.setValue();
//        return true;
//    }

//    @Transactional
//    public boolean deleteOption(String title){
//        Option object = optionRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Option.class));
//        optionRepository.delete(object);
//        return true;
//    }
}
