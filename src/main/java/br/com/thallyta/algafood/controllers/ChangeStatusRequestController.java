package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.services.ChangeStatusRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/requests/{code}")
public class ChangeStatusRequestController {

    @Autowired
    private ChangeStatusRequestService changeStatusRequestService;

    @PutMapping("/confirm")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void confirmRequest(@PathVariable String code) {
        changeStatusRequestService.confirmRequest(code);
    }

    @PutMapping("/deliver")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deliverRequest(@PathVariable String code) {
        changeStatusRequestService.deliverRequest(code);
    }

    @PutMapping("/cancel")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void cancelRequest(@PathVariable String code) {
        changeStatusRequestService.cancelRequest(code);
    }
}
