package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.repositories.queries.daily_sales.DailySalesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailySalesRepository extends JpaRepository<Request, Long>, DailySalesQueries {
}
