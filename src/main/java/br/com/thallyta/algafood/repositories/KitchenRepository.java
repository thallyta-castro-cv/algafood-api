package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    List<Kitchen> getByName(String name);

    List<Kitchen> findAllByNameContaining(String name);

    boolean existsByName(String name);

}
