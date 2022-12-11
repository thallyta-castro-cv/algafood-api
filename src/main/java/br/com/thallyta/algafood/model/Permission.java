package br.com.thallyta.algafood.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_permissions")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;
}
