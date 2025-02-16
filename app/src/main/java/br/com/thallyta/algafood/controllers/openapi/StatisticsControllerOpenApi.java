package br.com.thallyta.algafood.controllers.openapi;

import br.com.thallyta.algafood.controllers.v1.StatisticsController;
import br.com.thallyta.algafood.models.dtos.v1.responses.DailySalesResponseDTO;
import br.com.thallyta.algafood.models.params.ListDailySalesParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatísticas")
public interface StatisticsControllerOpenApi {

    @Operation(hidden = true)
    StatisticsController.StatisticsModel statistics();

    @Operation(
            summary = "Consulta estatísticas de vendas diárias",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "restauranteId", description = "ID do restaurante", example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "dataCriacaoInicio", description = "Data/hora inicial da criação do pedido", example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(in = ParameterIn.QUERY, name = "dataCriacaoFim", description = "Data/hora final da criação do pedido", example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
            }
    )
    List<DailySalesResponseDTO> getDailySales(ListDailySalesParams params,
                                              String timeOffset);

}
