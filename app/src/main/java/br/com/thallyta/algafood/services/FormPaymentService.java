package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.FormPayment;
import br.com.thallyta.algafood.repositories.FormPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormPaymentService {

    @Autowired
    private FormPaymentRepository formPaymentRepository;

    @Transactional
    public FormPayment save(FormPayment formPayment) {
        return formPaymentRepository.save(formPayment);
    }

    @Transactional
    public void delete(Long id){
        try{
            formPaymentRepository.deleteById(id);
            formPaymentRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new EntityExceptionInUse("Forma de pagamento não pode ser removida, pois está em uso.");
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Forma de pagamento não encontrada!");
        }
    }

    public FormPayment findOrFail(Long id) {
        return formPaymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Forma de pagamento não encontrada!"));
    }

    public List<FormPayment> getAll() {
        return formPaymentRepository.findAll();
    }
}
