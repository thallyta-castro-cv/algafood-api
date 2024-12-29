package br.com.thallyta.algafood.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<FormPayment> formsPayment = new HashSet<>();

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

    @Column(name = "open", nullable = false)
    private Boolean open = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "tb_restaurant_responsible",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserSystem> responsible = new HashSet<>();

    public boolean acceptPaymentMethod(FormPayment formPayment) {
        return getFormsPayment().contains(formPayment);
    }

    public boolean notAcceptPaymentMethod(FormPayment formPayment) {
        return !acceptPaymentMethod(formPayment);
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isClose() {
        return !isOpen();
    }

    public boolean isInactive() {
        return !isActive();
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean allowsOpen() {
        return isActive() && isClose();
    }

    public boolean allowsActive() {
        return isInactive();
    }

    public boolean allowsInactive() {
        return isActive();
    }

    public boolean allowsClose() {
        return isOpen();
    }
}
