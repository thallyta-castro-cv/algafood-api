package br.com.thallyta.algafood.models.dtos.v2.links;

import br.com.thallyta.algafood.controllers.v2.KitchenControllerV2;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class AlgaLinksV2 {

    public Link linkToKitchens(String rel) {
        return linkTo(KitchenControllerV2.class).withRel(rel);
    }

    public Link linkToKitchens() {
        return linkToKitchens(IanaLinkRelations.SELF.value());
    }
}
