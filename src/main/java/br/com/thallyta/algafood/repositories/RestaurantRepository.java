package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.repositories.queries.restaurant.RestaurantQueries;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantQueries {

    @Query("from Restaurant where name like %:name% and kitchen.id = :id")
    List<Restaurant> findByNameAndKitchen(String name, @Param("id") Long kitchenId);

    @Query("from Restaurant restaurant "   +
           "join fetch restaurant.kitchen ")
    @NotNull
    List<Restaurant> findAll();

    @Query("select case when count(1) > 0 then true else false end " +
            "from Restaurant restaurant " +
            "join restaurant.responsible responsible " +
            "where restaurant.id = :restaurantId " +
            "and responsible.id = :userId")
    boolean existsResponsible(@Param("restaurantId") Long restaurantId,
                              @Param("userId") Long userId);
}
