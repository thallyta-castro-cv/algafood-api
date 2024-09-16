package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.OrderController;
import br.com.thallyta.algafood.controllers.RestaurantProductController;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.dtos.responses.OrderResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderResponseDTOAssembler extends RepresentationModelAssemblerSupport<Request, OrderResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public OrderResponseDTOAssembler() {
        super(OrderController.class, OrderResponseDTO.class);
    }

    @Override
    public @NotNull OrderResponseDTO toModel(@NotNull Request request) {
        OrderResponseDTO order = createModelWithId(request.getCode(), request);
        modelMapper.map(request, order);

        order.add(linkTo(OrderController.class).withRel("requests"));

        order.getItems().forEach(item -> {
            item.add(linkTo(methodOn(RestaurantProductController.class)
                    .finById(order.getRestaurantId(), item.getProductId()))
                    .withRel("products"));
        });

        return order;
    }
}
