package edu.suai.recommendations.service;

import edu.suai.recommendations.exception.NotFoundException;
import edu.suai.recommendations.model.Shop;
import edu.suai.recommendations.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    @Transactional
    public boolean createShop(Shop shop){
        shopRepository.save(shop);
        return true;
    }

    @Transactional
    public boolean updateShop(Shop shop){
        Shop object = shopRepository.findByTitle(shop.getTitle()).orElseThrow(() -> new NotFoundException(Shop.class));
        object.setUrl(shop.getUrl());
        return true;
    }

    @Transactional
    public boolean deleteShop(String title){
        Shop object = shopRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Shop.class));
        shopRepository.delete(object);
        return true;
    }

    @Transactional(readOnly = true)
    public Shop getShopById(long id){
        return shopRepository.findById(id).orElseThrow(() -> new NotFoundException(Shop.class));
    }

    @Transactional(readOnly = true)
    public Shop getShopByTitle(String title) {
        return shopRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Shop.class));
    }

    @Transactional(readOnly = true)
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

}
