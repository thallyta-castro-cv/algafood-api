package br.com.thallyta.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@Component
@Configuration("algafood.mail")
public class EmailProperties {

    @NotNull
    private String sender;
}
