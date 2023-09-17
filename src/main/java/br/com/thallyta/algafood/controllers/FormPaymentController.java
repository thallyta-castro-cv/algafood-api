package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.models.assembler.request.FormPaymentDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.FormPaymentResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.FormPaymentRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.FormPaymentResponseDTO;
import br.com.thallyta.algafood.repositories.FormPaymentRepository;
import br.com.thallyta.algafood.services.FormPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/forms-payment")
public class FormPaymentController {

    @Autowired
    private FormPaymentRepository formPaymentRepository;

    @Autowired
    private FormPaymentService formPaymentService;

    @Autowired
    private FormPaymentResponseDTOAssembler formPaymentAssembler;

    @Autowired
    private FormPaymentDTODisassembler formPaymentDisassembler;

    @GetMapping
    public List<FormPaymentResponseDTO> getAll(){
        List<FormPayment> formPayments = formPaymentService.getAll();
        return formPaymentAssembler.toCollectionModel(formPayments);
    }

    @GetMapping("/{id}")
    public FormPaymentResponseDTO getById(@PathVariable Long id){
        FormPayment formPayment =  formPaymentService.findOrFail(id);
        return formPaymentAssembler.toFormPaymentResponse(formPayment);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public FormPaymentResponseDTO create(@RequestBody @Valid FormPaymentRequestDTO formPaymentRequestDTO) {
        FormPayment formPayment = formPaymentDisassembler.toDomainObject(formPaymentRequestDTO);
        return formPaymentAssembler.toFormPaymentResponse(formPaymentService.save(formPayment));
    }

    @PutMapping("/{id}")
    public FormPaymentResponseDTO update(@PathVariable Long id, @RequestBody @Valid FormPaymentRequestDTO formPaymentRequestDTO) {
        FormPayment formPaymentFound =  formPaymentService.findOrFail(id);
        formPaymentDisassembler.copyToDomainObject(formPaymentRequestDTO, formPaymentFound);
        return formPaymentAssembler.toFormPaymentResponse(formPaymentService.save(formPaymentFound));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        formPaymentService.delete(id);
    }
}
