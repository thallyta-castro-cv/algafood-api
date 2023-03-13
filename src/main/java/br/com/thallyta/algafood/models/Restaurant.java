package br.com.thallyta.algafood.models;

import br.com.thallyta.algafood.core.validation.annotation.ShippingFee;
import br.com.thallyta.algafood.core.validation.annotation.ValueZeroIncludeDescription;
import br.com.thallyta.algafood.core.validation.groups.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_restaurants")
@Data
@ValueZeroIncludeDescription(fieldValue = "shippingFee", fieldDescription = "name", requireDescription = "Frete Grátis")
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @ShippingFee
    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.RestaurantService.class)
    @NotNull
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
