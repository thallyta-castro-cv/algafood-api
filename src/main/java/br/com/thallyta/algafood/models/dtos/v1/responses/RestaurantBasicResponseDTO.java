package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Setter
@Getter
public class RestaurantBasicResponseDTO extends RepresentationModel<RestaurantBasicResponseDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String name;

    @ApiModelProperty(example = "12.00")
    private BigDecimal shippingFee;

    private KitchenResponseDTO kitchen;

}
