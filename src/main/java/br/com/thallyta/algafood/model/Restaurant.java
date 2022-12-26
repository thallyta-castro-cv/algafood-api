package br.com.thallyta.algafood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Kitchen kitchen;

    @ManyToMany
    @JoinTable(name = "tb_restaurant_form_payment",
               joinColumns = @JoinColumn(name = "restaurant_id"),
               inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
    private List<FormPayment> formsPayment = new ArrayList<>();

    @JsonIgnore
    @Embedded
    private Address address;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime updatedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

}
