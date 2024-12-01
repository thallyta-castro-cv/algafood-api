package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.v1.openapi.StatisticsControllerOpenapi;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.models.dtos.v1.responses.DailySalesResponseDTO;
import br.com.thallyta.algafood.models.params.ListDailySalesParams;
import br.com.thallyta.algafood.repositories.DailySalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
public class StatisticsController implements StatisticsControllerOpenapi {

    @Autowired
    private DailySalesRepository dailySalesRepository;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @CheckSecurity.Statistics.CanGet
    public StatisticsModel statistics() {
        var statisticsModel = new StatisticsModel();
        statisticsModel.add(algaLinks.linkToStatisticsDailySales("daily-sales"));
        return statisticsModel;
    }

    @GetMapping("/daily-sales")
    @CheckSecurity.Statistics.CanGet
    public List<DailySalesResponseDTO> getDailySales(ListDailySalesParams params,
                                                     @RequestParam(required= false, defaultValue = "+00:00") String timeOffset){
       return dailySalesRepository.getDailySales(params, timeOffset);
    }

    public static class StatisticsModel extends RepresentationModel<StatisticsModel> {
    }
}
