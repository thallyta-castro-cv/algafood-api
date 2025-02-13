package br.com.thallyta.algafood.core.api;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Objects;

@UtilityClass
public class ResourceUriHelper {

    public static void addUriInResponseHeader(Object resourceId) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(resourceId).toUri();

        HttpServletResponse response = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();

        Objects.requireNonNull(response).setHeader(HttpHeaders.LOCATION, uri.toString());
    }
}
