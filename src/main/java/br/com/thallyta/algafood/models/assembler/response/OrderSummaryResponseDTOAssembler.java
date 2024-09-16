package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.OrderController;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.dtos.responses.OrderSummaryResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class OrderSummaryResponseDTOAssembler extends
        RepresentationModelAssemblerSupport<Request, OrderSummaryResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public OrderSummaryResponseDTOAssembler() {
        super(OrderController.class, OrderSummaryResponseDTO.class);
    }

    @Override
    public @NotNull OrderSummaryResponseDTO toModel(@NotNull Request request) {
        OrderSummaryResponseDTO order = createModelWithId(request.getCode(), request);
        modelMapper.map(request, order);
        order.add(linkTo(OrderController.class).withRel("requests"));
        return order;
    }
}
