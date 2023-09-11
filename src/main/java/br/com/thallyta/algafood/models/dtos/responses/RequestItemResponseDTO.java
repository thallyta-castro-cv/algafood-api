package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestItemResponseDTO {

    private Long productId;
    private String productName;
    private Integer amount;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String note;
}
