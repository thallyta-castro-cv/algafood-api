package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.OrderController;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.OrderSummaryResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderSummaryResponseDTOAssembler extends
        RepresentationModelAssemblerSupport<Request, OrderSummaryResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks links;

    public OrderSummaryResponseDTOAssembler() {
        super(OrderController.class, OrderSummaryResponseDTO.class);
    }

    @Override
    public @NotNull OrderSummaryResponseDTO toModel(@NotNull Request request) {
        OrderSummaryResponseDTO order = createModelWithId(request.getCode(), request);
        modelMapper.map(request, order);

        if (request.canBeConfirmed()) {
            order.add(links.linkToConfirmationOrder(order.getCode(), "confirm-order"));
        }

        if (request.canBeCanceled()) {
            order.add(links.linkToCancelOrder(order.getCode(), "cancel-order"));
        }

        if (request.canBeDeliver()) {
            order.add(links.linkToDeliverOrder(order.getCode(), "deliver-order"));
        }

        order.add(links.linkToOrders());
        return order;
    }
}
