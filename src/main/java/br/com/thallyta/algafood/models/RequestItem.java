package br.com.thallyta.algafood.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="tb_request_item")
@Data
public class RequestItem {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Request request;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
}
