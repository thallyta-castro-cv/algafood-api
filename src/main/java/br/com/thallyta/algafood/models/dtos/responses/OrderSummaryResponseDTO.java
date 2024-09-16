package br.com.thallyta.algafood.models.dtos.responses;

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

    private String code;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalValue;
    private String requestStatus;
    private OffsetDateTime createdDate;
    private Long restaurantId;
    private String restaurantName;
    private Long clientId;
    private String clientName;
    private String clientEmail;
}
