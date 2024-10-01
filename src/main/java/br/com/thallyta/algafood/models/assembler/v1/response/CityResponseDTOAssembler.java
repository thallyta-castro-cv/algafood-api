package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.CityController;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.CityResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CityResponseDTOAssembler
        extends RepresentationModelAssemblerSupport<City, CityResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public CityResponseDTOAssembler() {
        super(CityController.class, CityResponseDTO.class);
    }

    @Override
    public @NotNull CityResponseDTO toModel(@NotNull City city) {
        CityResponseDTO cityDTO = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityDTO);
        cityDTO.add(algaLinks.linkToCities("cities"));
        return cityDTO;
    }

    @Override
    public @NotNull CollectionModel<CityResponseDTO> toCollectionModel(@NotNull Iterable<? extends City> cities) {
        return super.toCollectionModel(cities)
                .add(algaLinks.linkToCities());
    }
}
