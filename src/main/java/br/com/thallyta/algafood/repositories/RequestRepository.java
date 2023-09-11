package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("from Request p join fetch p.client join fetch p.restaurant r join fetch r.kitchen")
    List<Request> findAll();
}
