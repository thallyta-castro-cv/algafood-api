package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.openapi.DailySalesControllerOpenapi;
import br.com.thallyta.algafood.models.dtos.responses.DailySalesResponseDTO;
import br.com.thallyta.algafood.models.params.ListDailySalesParams;
import br.com.thallyta.algafood.repositories.DailySalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class DailySalesController implements DailySalesControllerOpenapi {

    @Autowired
    private DailySalesRepository dailySalesRepository;

    @GetMapping("/daily-sales")
    public List<DailySalesResponseDTO> getDailySales(ListDailySalesParams params,
                                                     @RequestParam(required= false, defaultValue = "+00:00") String timeOffset){
       return dailySalesRepository.getDailySales(params, timeOffset);
    }
}
