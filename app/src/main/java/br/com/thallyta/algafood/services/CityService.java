package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.core.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateService stateService;

    @Transactional
    public City save(City city) {
         Long stateId = city.getState().getId();
         State state = stateService.findOrFail(stateId);
         city.setState(state);
         return cityRepository.save(city);
    }

    @Transactional
    public void delete(Long id){
        try{
           cityRepository.deleteById(id);
           cityRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new EntityExceptionInUse("Cidade não pode ser removida, pois está em uso.");
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Cidade não encontrada!");
        }
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City findOrFail(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada!"));
    }
}
