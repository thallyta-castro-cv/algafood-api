package br.com.thallyta.algafood.repositories.specs;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.params.ListRequestParams;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;


public class RequestSpecs {

    public static Specification<Request> usingParams(ListRequestParams params) {
        return (root, query, builder) -> {
            if (Request.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("kitchen");
                root.fetch("client");
            }

            var predicates =  new ArrayList<Predicate>();

            if (params.getClientId() != null) {
                predicates.add(builder.equal(root.get("client").get("id"), params.getClientId()));
            }

            if (params.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant").get("id"), params.getRestaurantId()));
            }

            if (params.getCreatedDateStart() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), params.getCreatedDateStart()));
            }

            if (params.getCreatedDateEnd() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdDate"), params.getCreatedDateEnd()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
