package br.com.thallyta.algafood.models.dtos.v1.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DailySalesResponseDTO {

    private Date date;
    private Long totalSales;
    private BigDecimal totalBilled;

    public DailySalesResponseDTO(java.sql.Date date, Long totalSales, BigDecimal totalBilled) {
        this.date = date;
        this.totalSales = totalSales;
        this.totalBilled = totalBilled;
    }
}
