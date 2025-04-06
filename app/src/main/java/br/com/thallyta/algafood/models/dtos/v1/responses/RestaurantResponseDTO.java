package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Getter
@Setter
@Relation(collectionRelation = "restaurants")
public class RestaurantResponseDTO extends RepresentationModel<RestaurantResponseDTO> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Thai Gourmet")
    private String name;

    @Schema(example = "12.00")
    private BigDecimal shippingFee;

    @Schema(example = "1")
    private Long kitchenId;

    @Schema(example = "Brasileira")
    private String kitchenName;

    @Schema(example = "true")
    private Boolean active;

    @Schema(example = "true")
    private Boolean open;

    @Schema(example = "24808-888")
    private String addressCep;

    @Schema(example = "Rua Floriano Peixoto")
    private String addressStreet;

    @Schema(example = "111")
    private String addressNumber;

    @Schema(example = "Apto 1")
    private String addressComplement;

    @Schema(example = "Centro")
    private String addressNeighborhood;

    @Schema(example = "1")
    private Long addressCityId;

    @Schema(example = "Rio de Janeiro")
    private String addressCityName;

    @Schema(example = "1")
    private Long addressCityStateId;

    @Schema(example = "Rio de Janeiro")
    private String addressCityStateName;
}
