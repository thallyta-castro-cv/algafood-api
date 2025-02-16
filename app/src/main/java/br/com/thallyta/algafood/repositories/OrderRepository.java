package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.Request;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Request, Long>,
        JpaSpecificationExecutor<Request> {

    @Query("from Request p join fetch p.client join fetch p.restaurant r join fetch r.kitchen")
    @NotNull
    List<Request> findAll();

    Optional<Request> findByCode(String code);

    @Query("SELECT CASE WHEN COUNT(req) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Request req " +
            "JOIN req.restaurant r " +
            "JOIN r.responsible responsible " +
            "WHERE req.code = :code " +
            "AND responsible.id = :userId")
    boolean isOrderManageFor(@Param("code") String code,
                             @Param("userId") Long userId);

}
