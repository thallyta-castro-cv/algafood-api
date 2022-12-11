package br.com.thallyta.algafood.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_cities")
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private State state;
}
