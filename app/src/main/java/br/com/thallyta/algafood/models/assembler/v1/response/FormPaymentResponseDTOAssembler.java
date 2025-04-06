package br.com.thallyta.algafood.models.assembler.v1.response;

import br.com.thallyta.algafood.controllers.v1.FormPaymentController;
import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.FormPaymentResponseDTO;
import br.com.thallyta.algafood.services.AccessService;
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

    @Autowired
    private AccessService accessService;

    public FormPaymentResponseDTOAssembler() {
        super(FormPaymentController.class, FormPaymentResponseDTO.class);
    }

    @Override
    public @NotNull FormPaymentResponseDTO toModel(@NotNull FormPayment formPayment) {
        FormPaymentResponseDTO formPaymentDTO =
                createModelWithId(formPayment.getId(), formPayment);

        modelMapper.map(formPayment, formPaymentDTO);

        if (accessService.canGetFormPayment()){
            formPaymentDTO.add(algaLinks.linkToFormPayments("form-payments"));
        }

        return formPaymentDTO;
    }

    @Override
    public @NotNull CollectionModel<FormPaymentResponseDTO> toCollectionModel(@NotNull Iterable<? extends FormPayment> entities) {
        CollectionModel<FormPaymentResponseDTO> collectionModel = super.toCollectionModel(entities);

        if (accessService.canGetFormPayment()) {
            collectionModel.add(algaLinks.linkToFormPayments());
        }

        return collectionModel;
    }
}
