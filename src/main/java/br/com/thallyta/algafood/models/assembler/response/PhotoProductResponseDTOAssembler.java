package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.RestaurantProductPhotoController;
import br.com.thallyta.algafood.models.ProductPhoto;
import br.com.thallyta.algafood.models.assembler.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.responses.ProductPhotoResponseDTO;
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

    public PhotoProductResponseDTOAssembler() {
        super(RestaurantProductPhotoController.class, ProductPhotoResponseDTO.class);
    }

    @Override
    public @NotNull ProductPhotoResponseDTO toModel(@NotNull ProductPhoto photo) {
        ProductPhotoResponseDTO photoDTO = modelMapper.map(photo, ProductPhotoResponseDTO.class);

        photoDTO.add(algaLinks.linkToProductPhoto(
                photo.getProduct().getRestaurant().getId(), photo.getProduct().getId()));

        photoDTO.add(algaLinks.linkToProducts(
                photo.getProduct().getRestaurant().getId(), photo.getProduct().getId(), "product"));

        return photoDTO;
    }
}
