package br.com.thallyta.algafood.models.mixin;

import br.com.thallyta.algafood.models.Address;
import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestaurantMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private Kitchen kitchen;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private List<FormPayment> formsPayment = new ArrayList<>();

    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}
