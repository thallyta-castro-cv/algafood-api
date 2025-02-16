package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Relation(collectionRelation = "requests")
public class OrderSummaryResponseDTO extends RepresentationModel<OrderSummaryResponseDTO> {

    @Schema(example = "04813f77-79b5-11ec-9a17-0242ac1b0002")
    private String code;

    @Schema(example = "298.90")
    private BigDecimal subtotal;

    @Schema(example = "10.00")
    private BigDecimal shippingFee;

    @Schema(example = "308.90")
    private BigDecimal totalValue;

    @Schema(example = "CRIADO")
    private String requestStatus;

    @Schema(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime createdDate;

    @Schema(example = "Tay Gourmet")
    private RestaurantOnlyNameResponseDTO restaurant;

    @Schema(example = "1")
    private Long clientId;

    @Schema(example = "Geraldo Silva")
    private String clientName;

    @Schema(example = "geraldo@email.com")
    private String clientEmail;
}
