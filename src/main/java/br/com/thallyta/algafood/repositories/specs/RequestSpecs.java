package br.com.thallyta.algafood.repositories.specs;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.params.ListRequestParams;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;


public class RequestSpecs {

    public static Specification<Request> usingParams(ListRequestParams params) {
        return (root, query, builder) -> {
            root.fetch("client");
            root.fetch("restaurant").fetch("kitchen");

            var predicates =  new ArrayList<Predicate>();

            if (params.getClientId() != null) {
                predicates.add(builder.equal(root.get("client"), params.getClientId())                );
            }

            if (params.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant"), params.getRestaurantId())                );
            }

            if (params.getCreatedDateStart() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), params.getCreatedDateStart())                );
            }

            if (params.getCreatedDateEnd() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdDate"), params.getCreatedDateEnd())                );
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
