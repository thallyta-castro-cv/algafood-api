package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.model.FormPayment;

import java.util.List;

public interface FormPaymentRepository {

    List<FormPayment> getAll();
    FormPayment getById(Long id);
    FormPayment save(FormPayment formPayment);
    void delete(FormPayment formPayment);

}
