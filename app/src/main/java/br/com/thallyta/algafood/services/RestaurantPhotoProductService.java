package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.ProductPhoto;
import br.com.thallyta.algafood.repositories.ProductRepository;
import br.com.thallyta.algafood.services.storage.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantPhotoProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PhotoStorageService photoLocalStorageService;

    @Transactional
    public ProductPhoto save (ProductPhoto photo, InputStream inputStream) {
        Long restaurantId = photo.getProduct().getRestaurant().getId();
        Long productId = photo.getProduct().getId();
        String newFileName = photoLocalStorageService.generateFileName(photo.getFileName());
        String fileNameFound = null;


        Optional<ProductPhoto> photoOptional =
                productRepository.findPhotoById(restaurantId, productId);

        if(photoOptional.isPresent()){
            fileNameFound = photoOptional.get().getFileName();
            productRepository.delete(photoOptional.get());
        }

        photo.setFileName(newFileName);
        photo = productRepository.save(photo);
        productRepository.flush();

        PhotoStorageService.NewPhoto newPhoto = PhotoStorageService.NewPhoto.builder()
                .fileName(photo.getFileName())
                .contentType(photo.getContentType())
                .inputStream(inputStream)
                .build();

        photoLocalStorageService.replace(fileNameFound, newPhoto);
        return photo;
    }

    @Transactional
    public void delete(Long restaurantId, Long productId) {
        ProductPhoto photo = findOrFail(restaurantId, productId);
        productRepository.delete(photo);
        productRepository.flush();
        photoLocalStorageService.remove(photo.getFileName());
    }

    public ProductPhoto findOrFail(Long restaurantId, Long productId){
        return productRepository.findPhotoById(restaurantId, productId)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado uma foto de produto para o restaurante e/ou produto informado"));
    }

    public void verifyCompatibilityMediaType(MediaType mediaType, String acceptHeader) throws HttpMediaTypeNotAcceptableException {

        List<MediaType> mediaTypes = MediaType.parseMediaTypes(acceptHeader);
        boolean compatible = mediaTypes.stream().anyMatch(mediaTypesAccept -> mediaTypesAccept.isCompatibleWith(mediaType));

        if(!compatible)
            throw new HttpMediaTypeNotAcceptableException(mediaTypes);
    }
}
