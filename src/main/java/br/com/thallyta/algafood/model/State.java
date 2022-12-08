package br.com.thallyta.algafood.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_permission")
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
