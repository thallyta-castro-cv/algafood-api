package br.com.thallyta.algafood.controllers.v1.openapi;

import br.com.thallyta.algafood.controllers.v1.StatisticsController;
import br.com.thallyta.algafood.models.dtos.v1.responses.DailySalesResponseDTO;
import br.com.thallyta.algafood.models.params.ListDailySalesParams;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Estatísticas")
public interface StatisticsControllerOpenapi {

    @ApiOperation(value = "Estatísticas", hidden = true)
    StatisticsController.StatisticsModel statistics();

    @ApiOperation("Consulta estatísticas de vendas diárias")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restauranteId", value = "ID do restaurante",
                    example = "1", dataType = "int"),
            @ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido",
                    example = "2019-12-01T00:00:00Z", dataType = "date-time"),
            @ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido",
                    example = "2019-12-02T23:59:59Z", dataType = "date-time")
    })
    List<DailySalesResponseDTO> getDailySales(ListDailySalesParams params,
                                              @ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC",
                                                      defaultValue = "+00:00") String timeOffset);
}
