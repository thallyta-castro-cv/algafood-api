package br.com.thallyta.algafood.models;

import br.com.thallyta.algafood.models.enums.RequestStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_request")
@Data
public class Request {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    @Column(name = "totalValue")
    private BigDecimal totalValue;

    @Embedded
    @Column(name = "address", nullable = false)
    private Address address;

    @Column(name="request_status")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus = RequestStatus.CREATED;

    @CreationTimestamp
    @Column(name="created_date", columnDefinition = "datetime")
    private OffsetDateTime createdDate;

    @Column(name="confirmation_date")
    private OffsetDateTime confirmationDate;

    @Column(name="cancellation_date")
    private OffsetDateTime cancellationDate;

    @Column(name="delivery_date")
    private OffsetDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="form_payment_id", nullable = false)
    private FormPayment formPayment;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "request")
    private List<RequestItem> items = new ArrayList<>();

    public void sumTotalValue() {
        this.subtotal = getItems().stream()
                .map(RequestItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalValue = this.subtotal.add(this.shippingFee);
    }

    public void defineShippingFee() {
        setShippingFee(getRestaurant().getShippingFee());
    }

    public void assignOrderToItems() {
        getItems().forEach(item -> item.setRequest(this));
    }

}
