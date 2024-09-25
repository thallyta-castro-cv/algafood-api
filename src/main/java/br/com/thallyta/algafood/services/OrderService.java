package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.data.PageableTranslator;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.repositories.RequestRepository;
import br.com.thallyta.algafood.validates.OrderValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private OrderValidate orderValidate;

    @Transactional
    public Request issueRequest(Request request) {
        orderValidate.validateRequest(request);
        orderValidate.validateItems(request);
        request.setShippingFee(request.getRestaurant().getShippingFee());
        request.sumTotalValue();
        return requestRepository.save(request);
    }

    public Request findOrFail(String code) {
        return requestRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Request não encontrado!"));
    }

    public Pageable translatePageable(Pageable pageable){
        var mapping = Map.of(
                "clientName", "client.name",
                "clientEmail", "client.email",
                "clientId", "client.id",
                "restaurantId", "restaurant.id",
                "restaurantName", "restaurant.name"
        );
        return PageableTranslator.translate(pageable, mapping);
    }
}
