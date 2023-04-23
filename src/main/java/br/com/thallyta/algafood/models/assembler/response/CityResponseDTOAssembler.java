package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.dtos.responses.CityResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CityResponseDTO toCityResponse(City city) {
        return modelMapper.map(city, CityResponseDTO.class);
    }

    public List<CityResponseDTO> toCollectionModel(List<City> cities) {
        return cities.stream()
                .map(this::toCityResponse)
                .collect(Collectors.toList());
    }
}
