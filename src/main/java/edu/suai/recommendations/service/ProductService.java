package edu.suai.recommendations.service;

import edu.suai.recommendations.dto.all.ProductDto;
import edu.suai.recommendations.exception.NotFoundException;
import edu.suai.recommendations.model.Option;
import edu.suai.recommendations.model.Product;
import edu.suai.recommendations.model.User;
import edu.suai.recommendations.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static edu.suai.recommendations.common.Constants.SIGNIFICANCE;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OptionService optionService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<Product> getProductByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(Product.class));
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByTitleAndShop(ProductDto productDto) {
        List<Product> products = productRepository.findByTitle(productDto.getTitle());
        return products.stream().filter(product -> product.getShop().getTitle().equals(productDto.getShop())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Set<Option> getOptionsByProductId(Long id) {
        return productRepository.findById(id).get().getOptions();
    }


    @Transactional
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public void doneCreationProduct(long productId){
        productRepository.findById(productId);
        userService.recommendNewProduct(productRepository.findById(productId).get());
    }

    @Transactional
    public boolean addUserProduct(Long productId, Long userId){
        boolean isExist;
        Double diff;
        User user = userService.getUserById(userId);
        Product product = productRepository.findById(productId).get();
        product.getUsers().add(user);
        Set<Option> options = product.getOptions();
        for(Option pOption:options){
            isExist=false;
            for(Option uOption:user.getOptions()){
                if (uOption.getCriteria().getTitle().equals(pOption.getCriteria().getTitle())){
                    if (pOption.getCriteria().isNumeric()) {
                        isExist=true;
                        if (Double.parseDouble(pOption.getValue()) > Double.parseDouble(uOption.getValue())) {
                            diff = Double.parseDouble(pOption.getValue()) - Double.parseDouble(uOption.getValue());
                            uOption.setValue(String.valueOf(Double.parseDouble(uOption.getValue()) + diff * SIGNIFICANCE));
                        }
                    }
                    else{
                        pOption.getUsers().add(user);
                    }
                }
            }
            if (!isExist){
                Option newOption = Option.builder()
                        .value(pOption.getValue())
                        .criteria(pOption.getCriteria())
                        .users(Collections.singleton(user))
                        .build();
                newOption = optionService.createOptionWithoutProduct(newOption);
                user.getOptions().add(newOption);
            }
        }
        user.getProducts().add(product);
        return true;
    }

//    @Transactional
//    public boolean updateProduct(Product product){
//        Product object = productRepository.findByTitle(product.getTitle()).orElseThrow(() -> new NotFoundException(Product.class));
//        object.setDescription(product.getDescription());
//        object.set
//        return true;
//    }

    @Transactional
    public boolean deleteProduct(String title){
//        Product object = productRepository.findByTitle(title);
//        productRepository.delete(object);
        return true;
    }
}
