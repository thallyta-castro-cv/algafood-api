package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.UserSystem;
import br.com.thallyta.algafood.repositories.queries.user.UserQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserSystem, Long>, UserQueries {
    Optional<UserSystem> findByEmail(String email);
}
