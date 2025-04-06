package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.Product;
import br.com.thallyta.algafood.models.ProductPhoto;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.repositories.queries.product.ProductRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId,
                               @Param("product") Long productId);

    List<Product> findByRestaurant(Restaurant restaurant);

    @Query("from Product p where p.active = true and p.restaurant = :restaurant")
    List<Product> findByActiveByRestaurant(Restaurant restaurant);

    @Query("select f from ProductPhoto f join f.product p "
            + "where p.restaurant.id = :restaurantId and f.product.id = :productId")
    Optional<ProductPhoto> findPhotoById(Long restaurantId, Long productId);
}
