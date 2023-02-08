package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.FormPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long> {

}
