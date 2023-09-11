package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request findOrFail(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));
    }
}
