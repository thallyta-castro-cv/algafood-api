package br.com.thallyta.algafood.core.squiggly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SquigglyConfig {

    /**
     * Configura o Squiggly para usar JsonFilter para os endpoints de requests e restaurants.
     *
     * @param objectMapper - O objectMapper do Squiggly.
     * @return FilterRegistrationBean - O bean do filtro configurado.
     */
    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper) {
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider("fields", null));

        var urlPatterns = Arrays.asList("/requests/*", "/restaurants/*");
        FilterRegistrationBean<SquigglyRequestFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new SquigglyRequestFilter());
        filter.setOrder(1);
        filter.setUrlPatterns(urlPatterns);
        return filter;
    }
}
