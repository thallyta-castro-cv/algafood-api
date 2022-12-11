package br.com.thallyta.algafood.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_form_payments")
@Data
public class FormPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;
}
