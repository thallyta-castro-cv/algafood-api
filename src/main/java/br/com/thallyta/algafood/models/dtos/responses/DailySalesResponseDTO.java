package br.com.thallyta.algafood.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DailySalesResponseDTO {

    private Date date;
    private Long totalSales;
    private BigDecimal totalBilled;

}
