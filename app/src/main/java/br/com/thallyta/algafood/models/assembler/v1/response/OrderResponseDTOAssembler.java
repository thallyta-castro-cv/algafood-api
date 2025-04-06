package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.OrderController;
import br.com.thallyta.algafood.controllers.v1.RestaurantProductController;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.OrderResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
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

    @Autowired
    private AlgaLinks links;

    @Autowired
    private AccessService accessService;

    public OrderResponseDTOAssembler() {
        super(OrderController.class, OrderResponseDTO.class);
    }

    @Override
    public @NotNull OrderResponseDTO toModel(@NotNull Request request) {
        OrderResponseDTO order = createModelWithId(request.getCode(), request);
        modelMapper.map(request, order);

        if(accessService.canManageOrders(order.getCode())){
            if (request.canBeConfirmed()) {
                order.add(links.linkToConfirmationOrder(order.getCode(), "confirm-order"));
            }

            if (request.canBeCanceled()) {
                order.add(links.linkToCancelOrder(order.getCode(), "cancel-order"));
            }

            if (request.canBeDeliver()) {
                order.add(links.linkToDeliverOrder(order.getCode(), "deliver-order"));
            }
        }

        order.add(links.linkToOrders());

        if (accessService.canGetUserGroupPermission()) {
            order.getClient().add(
                    links.linkToUser(order.getClient().getId()));
        }

        if (accessService.canGetFormPayment()){
            order.getFormPayment().add(
                    links.linkToFormPayments(order.getFormPayment().getId()));
        }

        if (accessService.canGetRestaurants()){
            order.getItems().forEach(item -> item.add(linkTo(methodOn(RestaurantProductController.class)
                    .finById(order.getRestaurant().getId(), item.getProductId()))
                    .withRel("products")));
        }

        return order;
    }
}
