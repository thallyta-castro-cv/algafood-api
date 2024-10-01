package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.v1.openapi.ChangeStatusOrderControllerOpenApi;
import br.com.thallyta.algafood.services.ChangeStatusRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/requests/{code}")
public class ChangeStatusOrderController implements ChangeStatusOrderControllerOpenApi {

    @Autowired
    private ChangeStatusRequestService changeStatusRequestService;

    @PutMapping("/confirm")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirmRequest(@PathVariable String code){
        changeStatusRequestService.confirmRequest(code);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/deliver")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deliverRequest(@PathVariable String code) {
        changeStatusRequestService.deliverRequest(code);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancel")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelRequest(@PathVariable String code) {
        changeStatusRequestService.cancelRequest(code);
        return ResponseEntity.noContent().build();
    }
}
