package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.openapi.RestaurantProductControllerOpenApi;
import br.com.thallyta.algafood.models.Product;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.request.ProductRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.ProductResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.ProductRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.ProductResponseDTO;
import br.com.thallyta.algafood.repositories.ProductRepository;
import br.com.thallyta.algafood.services.ProductService;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController implements RestaurantProductControllerOpenApi {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductResponseDTOAssembler productAssembler;

    @Autowired
    private ProductRequestDTODisassembler productDisassembler;

    @GetMapping
    public List<ProductResponseDTO> getAll(@PathVariable Long restaurantId,
                                           @RequestParam(required = false) boolean includeActiveOnly) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        List<Product> products =  includeActiveOnly ? productRepository.findByActiveByRestaurant(restaurant) :
                productRepository.findByRestaurant(restaurant);

        return productAssembler.toCollectionModel(products);
    }

    @GetMapping("/{productId}")
    public ProductResponseDTO finById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.findOrFail(restaurantId, productId);
        return productAssembler.toProductResponse(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO save(@PathVariable Long restaurantId,
                                  @RequestBody @Valid ProductRequestDTO productRequest) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        Product product = productDisassembler.toDomainObject(productRequest);
        product.setRestaurant(restaurant);
        product = productService.save(product);
        return productAssembler.toProductResponse(product);
    }

    @PutMapping("/{productId}")
    public ProductResponseDTO update(@PathVariable Long restaurantId, @PathVariable Long productId,
                                  @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        Product currentProduct = productService.findOrFail(restaurantId, productId);
        productDisassembler.copyToDomainObject(productRequestDTO, currentProduct);
        currentProduct = productService.save(currentProduct);
        return productAssembler.toProductResponse(currentProduct);
    }
}
