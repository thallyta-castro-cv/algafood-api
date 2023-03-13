package br.com.thallyta.algafood.models;

import br.com.thallyta.algafood.core.validation.groups.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Entity
@Table(name="tb_cities")
@Data
public class City {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.StateService.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private State state;
}
