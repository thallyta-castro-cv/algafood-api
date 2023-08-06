package br.com.thallyta.algafood.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_restaurants")
@Data
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Kitchen kitchen;

    @ManyToMany
    @JoinTable(name = "tb_restaurant_form_payment",
               joinColumns = @JoinColumn(name = "restaurant_id"),
               inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
    private List<FormPayment> formsPayment = new ArrayList<>();

    @Embedded
    private Address address;

    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;
}