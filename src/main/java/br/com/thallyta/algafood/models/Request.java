package br.com.thallyta.algafood.models;

import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.models.enums.RequestStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tb_request")
@Getter
@Setter
public class Request {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

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
    private RequestStatus requestStatus = RequestStatus.CRIADO;

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

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<RequestItem> items = new ArrayList<>();

    public void sumTotalValue() {
       getItems().forEach(RequestItem::calculateTotalPrice);

        this.subtotal = getItems().stream()
                .map(RequestItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalValue = this.subtotal.add(this.shippingFee);
    }

    public void confirmRequest() {
        setRequestStatus(RequestStatus.CONFIRMADO);
        setConfirmationDate(OffsetDateTime.now());
    }

    public void cancelRequest() {
        setRequestStatus(RequestStatus.CANCELADO);
        setCancellationDate(OffsetDateTime.now());
    }

    public void deliverRequest() {
        setRequestStatus(RequestStatus.ENTREGUE);
        setDeliveryDate(OffsetDateTime.now());
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        if (getRequestStatus().doesNotChangeStatusTo(requestStatus)) {
           throw new BadRequestException("Status do pedido não pode ser alterado de "
                + getRequestStatus().getStatusValue() + " para " + requestStatus.getStatusValue());
        }

        this.requestStatus = requestStatus;
    }

    @PrePersist
    private void makeCode() {
        setCode(UUID.randomUUID().toString());
    }

}
