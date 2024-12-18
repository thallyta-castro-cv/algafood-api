package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.openapi.FormPaymentsControllerOpenApi;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.models.assembler.v1.request.FormPaymentDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.FormPaymentResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.FormPaymentRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.FormPaymentResponseDTO;
import br.com.thallyta.algafood.repositories.FormPaymentRepository;
import br.com.thallyta.algafood.services.FormPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/v1/forms-payment")
public class FormPaymentController implements FormPaymentsControllerOpenApi {

    @Autowired
    private FormPaymentRepository formPaymentRepository;

    @Autowired
    private FormPaymentService formPaymentService;

    @Autowired
    private FormPaymentResponseDTOAssembler formPaymentAssembler;

    @Autowired
    private FormPaymentDTODisassembler formPaymentDisassembler;

    @GetMapping
    @CheckSecurity.FormPayment.CanGet
    public ResponseEntity<CollectionModel<FormPaymentResponseDTO>> getAll(ServletWebRequest request){
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        OffsetDateTime lastUpdatedDate = formPaymentRepository.getUpdatedDate();
        String eTag = "0";

        if(lastUpdatedDate != null){
            eTag = String.valueOf(lastUpdatedDate.toEpochSecond());
        }

        if(request.checkNotModified(eTag)){
            return null;
        }

        List<FormPayment> formPayments = formPaymentService.getAll();
        CollectionModel<FormPaymentResponseDTO> formPaymentResponseDTOS =  formPaymentAssembler.toCollectionModel(formPayments);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formPaymentResponseDTOS);
    }

    @GetMapping("/{id}")
    @CheckSecurity.FormPayment.CanGet
    public ResponseEntity<FormPaymentResponseDTO> getById(@PathVariable Long id){
        FormPayment formPayment =  formPaymentService.findOrFail(id);
        FormPaymentResponseDTO formPaymentResponseDTO = formPaymentAssembler.toModel(formPayment);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .body(formPaymentResponseDTO);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @CheckSecurity.FormPayment.CanEdit
    public FormPaymentResponseDTO create(@RequestBody @Valid FormPaymentRequestDTO formPaymentRequestDTO) {
        FormPayment formPayment = formPaymentDisassembler.toDomainObject(formPaymentRequestDTO);
        return formPaymentAssembler.toModel(formPaymentService.save(formPayment));
    }

    @PutMapping("/{id}")
    @CheckSecurity.FormPayment.CanEdit
    public FormPaymentResponseDTO update(@PathVariable Long id, @RequestBody @Valid FormPaymentRequestDTO formPaymentRequestDTO) {
        FormPayment formPaymentFound =  formPaymentService.findOrFail(id);
        formPaymentDisassembler.copyToDomainObject(formPaymentRequestDTO, formPaymentFound);
        return formPaymentAssembler.toModel(formPaymentService.save(formPaymentFound));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.FormPayment.CanEdit
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        formPaymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
