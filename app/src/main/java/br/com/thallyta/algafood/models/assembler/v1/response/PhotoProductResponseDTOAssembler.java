package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.RestaurantProductPhotoController;
import br.com.thallyta.algafood.models.ProductPhoto;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.ProductPhotoResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PhotoProductResponseDTOAssembler extends
        RepresentationModelAssemblerSupport<ProductPhoto, ProductPhotoResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AccessService accessService;

    public PhotoProductResponseDTOAssembler() {
        super(RestaurantProductPhotoController.class, ProductPhotoResponseDTO.class);
    }

    @Override
    public @NotNull ProductPhotoResponseDTO toModel(@NotNull ProductPhoto photo) {
        ProductPhotoResponseDTO photoDTO = modelMapper.map(photo, ProductPhotoResponseDTO.class);

        if (accessService.canGetRestaurants()){
            photoDTO.add(algaLinks.linkToProductPhoto(
                    photo.getProduct().getRestaurant().getId(), photo.getProduct().getId()));

            photoDTO.add(algaLinks.linkToProducts(
                    photo.getProduct().getRestaurant().getId(), photo.getProduct().getId(), "product"));
        }


        return photoDTO;
    }
}
