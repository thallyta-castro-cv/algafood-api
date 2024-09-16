package br.com.thallyta.algafood.core.modelmapper;

import br.com.thallyta.algafood.models.RequestItem;
import br.com.thallyta.algafood.models.dtos.requests.OrderItemRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(OrderItemRequestDTO.class, RequestItem.class)
                .addMappings(mapper -> mapper.skip(RequestItem::setId));

        return modelMapper;
    }
}
