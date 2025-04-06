package br.com.thallyta.algafood.models.assembler.v1.request;

import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.dtos.v1.requests.CityRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityRequestDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public City toDomainObject(CityRequestDTO cityRequestDTO) {
        return modelMapper.map(cityRequestDTO, City.class);
    }

    public void copyToDomainObject(CityRequestDTO cityRequestDTO, City city) {
        city.setState(new State());
        modelMapper.map(cityRequestDTO, city);
    }
}
