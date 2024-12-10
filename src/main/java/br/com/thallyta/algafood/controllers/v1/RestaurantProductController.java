package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.Product;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.assembler.v1.request.ProductRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.ProductResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.ProductRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.ProductResponseDTO;
import br.com.thallyta.algafood.repositories.ProductRepository;
import br.com.thallyta.algafood.services.ProductService;
import br.com.thallyta.algafood.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products")
public class RestaurantProductController {

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

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    @CheckSecurity.Restaurants.CanGet
    public CollectionModel<ProductResponseDTO> getAll(@PathVariable Long restaurantId,
                                                      @RequestParam(required = false) Boolean includeActiveOnly) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);

        List<Product> allProducts = null;

        if (Boolean.TRUE.equals(includeActiveOnly)) {
            allProducts = productRepository.findByRestaurant(restaurant);
        } else {
            allProducts = productRepository.findByActiveByRestaurant(restaurant);
        }

        return productAssembler.toCollectionModel(allProducts)
                .add(algaLinks.linkToProducts(restaurantId));
    }

    @GetMapping("/{productId}")
    @CheckSecurity.Restaurants.CanGet
    public ProductResponseDTO finById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.findOrFail(restaurantId, productId);
        return productAssembler.toModel(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Restaurants.CanManagerOperation
    public ProductResponseDTO save(@PathVariable Long restaurantId,
                                  @RequestBody @Valid ProductRequestDTO productRequest) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        Product product = productDisassembler.toDomainObject(productRequest);
        product.setRestaurant(restaurant);
        product = productService.save(product);
        return productAssembler.toModel(product);
    }

    @PutMapping("/{productId}")
    @CheckSecurity.Restaurants.CanManagerOperation
    public ProductResponseDTO update(@PathVariable Long restaurantId, @PathVariable Long productId,
                                  @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        Product currentProduct = productService.findOrFail(restaurantId, productId);
        productDisassembler.copyToDomainObject(productRequestDTO, currentProduct);
        currentProduct = productService.save(currentProduct);
        return productAssembler.toModel(currentProduct);
    }
}
