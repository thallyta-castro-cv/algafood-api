package br.com.thallyta.algafood.models.dtos.v1.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDTO extends RepresentationModel<OrderItemResponseDTO> {

    @Schema(example = "1")
    private Long productId;

    @Schema(example = "Porco com molho agridoce")
    private String productName;

    @Schema(example = "2")
    private Integer amount;

    @Schema(example = "78.90")
    private BigDecimal unitPrice;

    @Schema(example = "157.80")
    private BigDecimal totalPrice;

    @Schema(example = "Menos picante, por favor")
    private String note;
}
