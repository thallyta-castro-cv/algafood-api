package br.com.thallyta.algafood.models.assembler.response;

import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.models.dtos.responses.FormPaymentResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormPaymentResponseDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormPaymentResponseDTO toFormPaymentResponse(FormPayment formPayment) {
        return modelMapper.map(formPayment, FormPaymentResponseDTO.class);
    }

    public List<FormPaymentResponseDTO> toCollectionModel(Collection<FormPayment> formPayments) {
        return formPayments.stream()
                .map(this::toFormPaymentResponse)
                .collect(Collectors.toList());
    }
}
