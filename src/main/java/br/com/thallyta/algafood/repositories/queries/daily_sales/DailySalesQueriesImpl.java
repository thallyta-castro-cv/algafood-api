package br.com.thallyta.algafood.repositories.queries.daily_sales;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.dtos.v1.responses.DailySalesResponseDTO;
import br.com.thallyta.algafood.models.enums.RequestStatus;
import br.com.thallyta.algafood.models.params.ListDailySalesParams;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailySalesQueriesImpl implements DailySalesQueries{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailySalesResponseDTO> getDailySales(ListDailySalesParams params, String timeOffset) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(DailySalesResponseDTO.class);
        var root = query.from(Request.class);

        var functionConvertTz = builder.function("convert_tz", LocalDate.class,
                root.get("createdDate"), builder.literal("+00:00"), builder.literal(timeOffset));

        var functionDate = builder.function("date", LocalDate.class, functionConvertTz);

        var selection= builder.construct(DailySalesResponseDTO.class,
                functionDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue")));

        getSqlParams(params, builder, root, query);

        query.select(selection);
        query.groupBy(functionDate);
        return entityManager.createQuery(query).getResultList();
    }

    private void  getSqlParams(ListDailySalesParams params, CriteriaBuilder builder,
                               Root<Request> root, CriteriaQuery<DailySalesResponseDTO> query) {

        var predicates = new ArrayList<Predicate>();

        if(params.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant").get("id"), params.getRestaurantId()));
        }

        if(params.getCreatedDateStart() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), params.getCreatedDateStart()));
        }

        if(params.getCreatedDateEnd() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), params.getCreatedDateEnd()));
        }

        predicates.add(root.get("requestStatus").in(
                RequestStatus.CONFIRMADO, RequestStatus.ENTREGUE));

        query.where(predicates.toArray(new Predicate[0]));
    }
}
