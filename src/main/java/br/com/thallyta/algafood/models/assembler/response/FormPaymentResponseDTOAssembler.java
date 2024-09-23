package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.controllers.FormPaymentController;
import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.models.assembler.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.responses.FormPaymentResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FormPaymentResponseDTOAssembler extends
        RepresentationModelAssemblerSupport<FormPayment, FormPaymentResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public FormPaymentResponseDTOAssembler() {
        super(FormPaymentController.class, FormPaymentResponseDTO.class);
    }

    @Override
    public @NotNull FormPaymentResponseDTO toModel(@NotNull FormPayment formPayment) {
        FormPaymentResponseDTO formPaymentDTO =
                createModelWithId(formPayment.getId(), formPayment);

        modelMapper.map(formPayment, formPaymentDTO);
        formPaymentDTO.add(algaLinks.linkToFormPayments("form-payments"));
        return formPaymentDTO;
    }

    @Override
    public @NotNull CollectionModel<FormPaymentResponseDTO> toCollectionModel(@NotNull Iterable<? extends FormPayment> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToFormPayments());
    }
}
