package br.com.thallyta.algafood.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="tb_form_payments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class FormPayment {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @UpdateTimestamp
    private OffsetDateTime updatedDate;
}
