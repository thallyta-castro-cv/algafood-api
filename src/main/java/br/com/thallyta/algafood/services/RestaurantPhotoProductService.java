package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.models.ProductPhoto;
import br.com.thallyta.algafood.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantPhotoProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save (ProductPhoto photo) {
        return productRepository.save(photo);
    }
}
