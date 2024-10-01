package br.com.thallyta.algafood.repositories.queries.daily_sales;

import br.com.thallyta.algafood.models.dtos.v1.responses.DailySalesResponseDTO;
import br.com.thallyta.algafood.models.params.ListDailySalesParams;

import java.util.List;

public interface DailySalesQueries {

    public List<DailySalesResponseDTO> getDailySales(ListDailySalesParams params, String timeOffset);
}
