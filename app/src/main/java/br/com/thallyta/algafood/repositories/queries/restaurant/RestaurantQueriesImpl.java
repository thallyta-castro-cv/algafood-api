package br.com.thallyta.algafood.repositories.queries.restaurant;


import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.params.ListRestaurantParams;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class RestaurantQueriesImpl implements RestaurantQueries {

    @Autowired
    EntityManager manager;

    @Override
    public List list(ListRestaurantParams params) {
        String sql = "select restaurant from Restaurant restaurant " +
                     "where ";

        sql += getSqlParams(params);
        Query query = getParameters(sql, params);
        return query.getResultList();
    }

    private String getSqlParams(@NotNull ListRestaurantParams params){
        String sqlParams = "";

        if (StringUtils.hasLength(params.getName())) {
            sqlParams += params.getName() != null ? " and name like :name " : "";
        }

        if (params.getShippingFeeInitial() != null) {
            sqlParams += params.getShippingFeeInitial() != null ? " and shippingFee >= :shippingFeeInitial" : "";
        }

        if (params.getShippingFeeFinal() != null) {
            sqlParams += params.getShippingFeeFinal() != null ? " and shippingFee <= :shippingFeeFinal" : "";
        }

        return sqlParams;
    }

    private Query getParameters(String sql, ListRestaurantParams params){

        Query query =  manager.createQuery(sql, Restaurant.class);

        if (StringUtils.hasLength(params.getName())) {
            query.setParameter("name", "%" + params.getName() + "%");
        }

        if (params.getShippingFeeInitial() != null) {
            query.setParameter("shippingFeeInitial", params.getShippingFeeInitial());
        }

        if (params.getShippingFeeFinal() != null) {
            query.setParameter("shippingFeeFinal", params.getShippingFeeFinal());
        }

        return query;
    }
}
