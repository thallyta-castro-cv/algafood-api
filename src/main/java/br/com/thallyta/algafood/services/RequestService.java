package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.repositories.RequestRepository;
import br.com.thallyta.algafood.validates.RequestValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestValidate requestValidate;

    @Transactional
    public Request issueRequest(Request request) {
        requestValidate.validateRequest(request);
        requestValidate.validateItems(request);
        request.setShippingFee(request.getRestaurant().getShippingFee());
        request.sumTotalValue();
        return requestRepository.save(request);
    }

    public Request findOrFail(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request não encontrado!"));
    }


}
