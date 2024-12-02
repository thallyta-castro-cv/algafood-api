package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.RestaurantProductController;
import br.com.thallyta.algafood.models.Product;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.ProductResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseDTOAssembler extends
        RepresentationModelAssemblerSupport<Product, ProductResponseDTO> {

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccessService accessService;

    public ProductResponseDTOAssembler() {
        super(RestaurantProductController.class, ProductResponseDTO.class);
    }

    @Override
    public @NotNull ProductResponseDTO toModel(@NotNull Product product) {
        ProductResponseDTO productDTO = createModelWithId(
                product.getId(), product, product.getRestaurant().getId());

        modelMapper.map(product, productDTO);

        if (accessService.canGetRestaurants()){
            productDTO.add(algaLinks.linkToProducts(product.getRestaurant().getId(), "products"));

            productDTO.add(algaLinks.linkToProductPhoto(
                    product.getRestaurant().getId(), product.getId(), "photo"));
        }

        return productDTO;
    }


    public @NotNull CollectionModel<ProductResponseDTO> toCollectionModel(
            @NotNull Iterable<? extends Product> entities, Long restaurantId) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToProducts(restaurantId));
    }
}
