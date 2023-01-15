package br.com.thallyta.algafood.model;

import br.com.thallyta.algafood.model.enums.RequestStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private RequestStatus requestStatus;

    @CreationTimestamp
    @Column(name="created_date", columnDefinition = "datetime")
    private LocalDateTime createdDate;

    @Column(name="confirmation_date")
    private LocalDateTime confirmationDate;

    @Column(name="cancellation_date")
    private LocalDateTime cancellationDate;

    @Column(name="delivery_date")
    private LocalDateTime deliveryDate;

    @ManyToOne
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

}
