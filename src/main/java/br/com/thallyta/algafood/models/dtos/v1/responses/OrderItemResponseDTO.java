package br.com.thallyta.algafood.models.dtos.v1.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDTO extends RepresentationModel<OrderItemResponseDTO> {

    private Long productId;
    private String productName;
    private Integer amount;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String note;
}
