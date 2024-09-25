package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.controllers.openapi.RestaurantProductPhotoControllerOpenApi;
import br.com.thallyta.algafood.models.Product;
import br.com.thallyta.algafood.models.ProductPhoto;
import br.com.thallyta.algafood.models.assembler.response.PhotoProductResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.ProductPhotoRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.ProductPhotoResponseDTO;
import br.com.thallyta.algafood.services.ProductService;
import br.com.thallyta.algafood.services.RestaurantPhotoProductService;
import br.com.thallyta.algafood.services.storage.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController implements RestaurantProductPhotoControllerOpenApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private RestaurantPhotoProductService restaurantProductService;

    @Autowired
    private PhotoProductResponseDTOAssembler photoProductResponseDTOAssembler;

    @Autowired
    private PhotoStorageService photoLocalStorageService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoResponseDTO updateFile(@PathVariable  Long restaurantId,
                                              @PathVariable Long productId,
                                              @Valid ProductPhotoRequestDTO productPhotoRequestDTO,
                                              @RequestPart(required = true) MultipartFile file) throws IOException {

        Product product = productService.findOrFail(restaurantId, productId);

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(productPhotoRequestDTO.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFileName(file.getOriginalFilename());
        ProductPhoto photoSaved = restaurantProductService.save(photo, file.getInputStream());

        return photoProductResponseDTOAssembler.toModel(photoSaved);
    }

    @GetMapping
    public ProductPhotoResponseDTO findById(@PathVariable Long restaurantId,
                                            @PathVariable Long productId) {
        ProductPhoto productPhoto = restaurantProductService.findOrFail(restaurantId, productId);
        return photoProductResponseDTOAssembler.toModel(productPhoto);
    }

    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> findFile(@PathVariable Long restaurantId,
                                                        @PathVariable Long productId,
                                                        @RequestHeader(name="accept") String acceptHeader)
                                                        throws HttpMediaTypeNotAcceptableException {

        try{
            ProductPhoto productPhoto = restaurantProductService.findOrFail(restaurantId, productId);
            MediaType mediaType = MediaType.parseMediaType(productPhoto.getContentType());
            restaurantProductService.verifyCompatibilityMediaType(mediaType, acceptHeader);
            PhotoStorageService.RecoverPhoto recoverPhoto = photoLocalStorageService.recover(productPhoto.getFileName());

            if(recoverPhoto.hasUrl()){
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, recoverPhoto.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok().contentType(mediaType)
                        .body(new InputStreamResource(recoverPhoto.getInputStream()));
            }

        } catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId,
                        @PathVariable Long productId) {
        restaurantProductService.delete(restaurantId, productId);
    }

}
