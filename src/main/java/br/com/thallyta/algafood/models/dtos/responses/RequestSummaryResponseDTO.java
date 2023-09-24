package br.com.thallyta.algafood.models.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonFilter("requestFilter")
@Getter
@Setter
public class RequestSummaryResponseDTO {

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
