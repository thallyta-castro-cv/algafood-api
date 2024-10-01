package br.com.thallyta.algafood.models.dtos.v1.responses;

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

    private String code;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalValue;
    private String requestStatus;
    private OffsetDateTime createdDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime cancellationDate;
    private String addressCep;
    private String addressStreet;
    private String addressNumber;
    private String addressComplement;
    private String addressNeighborhood;
    private Long addressCityId;
    private String addressCityName;
    private Long addressCityStateId;
    private String addressCityStateName;
    private RestaurantOnlyNameResponseDTO restaurant;
    private UserResponseDTO client;
    private FormPaymentResponseDTO formPayment;
    private List<OrderItemResponseDTO> items;
}
