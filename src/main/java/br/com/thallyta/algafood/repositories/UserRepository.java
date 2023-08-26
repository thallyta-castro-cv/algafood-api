package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.User;
import br.com.thallyta.algafood.repositories.queries.user.UserQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserQueries {
    Optional<User> findByEmail(String email);
}
