package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.models.FormPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long> {

    @Query("SELECT MAX(fp.updatedDate) FROM FormPayment fp")
    OffsetDateTime getUpdatedDate();

}
