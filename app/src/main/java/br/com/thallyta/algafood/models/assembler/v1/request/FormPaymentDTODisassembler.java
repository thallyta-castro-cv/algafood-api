package br.com.thallyta.algafood.models.assembler.v1.request;

import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.models.dtos.v1.requests.FormPaymentRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormPaymentDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormPayment toDomainObject(FormPaymentRequestDTO formPaymentRequestDTO) {
        return modelMapper.map(formPaymentRequestDTO, FormPayment.class);
    }

    public void copyToDomainObject(FormPaymentRequestDTO formPaymentRequestDTO, FormPayment formPayment) {
        modelMapper.map(formPaymentRequestDTO, formPayment);
    }
}
