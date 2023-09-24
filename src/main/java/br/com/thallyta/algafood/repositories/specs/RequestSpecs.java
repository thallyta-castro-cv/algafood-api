package br.com.thallyta.algafood.repositories.specs;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.models.params.ListRequestParams;
import org.springframework.data.jpa.domain.Specification;

public class RequestSpecs {

    public static Specification<Request> usingParams(ListRequestParams params) {
        return (root, query, builder) -> {
            root.fetch("client");
            root.fetch("restaurant").fetch("kitchen");

            var predicates = builder.conjunction();

            if (params.getClientId() != null) {
                predicates.getExpressions().add(
                        builder.equal(root.get("client"), params.getClientId())
                );
            }

            if (params.getRestaurantId() != null) {
                predicates.getExpressions().add(
                        builder.equal(root.get("restaurant"), params.getRestaurantId())
                );
            }

            if (params.getCreationDateStart() != null) {
                predicates.getExpressions().add(
                        builder.greaterThanOrEqualTo(root.get("createdDate"), params.getCreationDateStart())
                );
            }

            if (params.getCreationDateEnd() != null) {
                predicates.getExpressions().add(
                        builder.lessThanOrEqualTo(root.get("createdDate"), params.getCreationDateEnd())
                );
            }

            return predicates;
        };
    }
}
