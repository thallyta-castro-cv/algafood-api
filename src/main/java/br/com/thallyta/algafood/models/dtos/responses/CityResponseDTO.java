package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Data;

@Data
public class CityResponseDTO {

    private Long id;
    private String name;
    private Long stateId;
    private String stateName;
}
