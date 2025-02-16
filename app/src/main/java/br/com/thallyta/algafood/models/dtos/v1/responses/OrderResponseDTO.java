package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "requests")
@Getter
@Setter
public class OrderResponseDTO extends RepresentationModel<OrderResponseDTO> {

    @Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String code;

    @Schema(example = "298.90")
    private BigDecimal subtotal;

    @Schema(example = "10.00")
    private BigDecimal shippingFee;

    @Schema(example = "308.90")
    private BigDecimal totalValue;

    @Schema(example = "CRIADO")
    private String requestStatus;

    @Schema(example = "2022-12-01T20:34:04Z")
    private OffsetDateTime createdDate;

    @Schema(example = "2022-12-01T20:34:04Z")
    private OffsetDateTime confirmationDate;

    @Schema(example = "2022-12-01T20:34:04Z")
    private OffsetDateTime deliveryDate;

    @Schema(example = "2022-12-01T20:34:04Z")
    private OffsetDateTime cancellationDate;

    @Schema(example = "38400-000")
    private String addressCep;

    @Schema(example = "Rua Floriano Peixoto")
    private String addressStreet;

    @Schema(example = "\"1500\"")
    private String addressNumber;

    @Schema(example = "Apto 901")
    private String addressComplement;

    @Schema(example = "Centro")
    private String addressNeighborhood;

    @Schema(example = "1")
    private Long addressCityId;

    @Schema(example = "São Paulo")
    private String addressCityName;

    @Schema(example = "1")
    private Long addressCityStateId;

    @Schema(example = "são Paulo")
    private String addressCityStateName;

    private RestaurantOnlyNameResponseDTO restaurant;
    private UserResponseDTO client;
    private FormPaymentResponseDTO formPayment;
    private List<OrderItemResponseDTO> items;
}
