package br.com.thallyta.algafood.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="tb_restaurants")
public class Restaurant {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;
}
