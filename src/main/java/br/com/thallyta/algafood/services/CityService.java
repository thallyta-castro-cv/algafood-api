package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.common.exceptions.NotFound;
import br.com.thallyta.algafood.common.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.model.City;
import br.com.thallyta.algafood.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City save(City city) {
        try{
            Long stateId = city.getState().getId();
            cityRepository.findById(stateId);
            return cityRepository.save(city);
        } catch(DataIntegrityViolationException exception){
            throw new ValidateMessageException("Não foi encontrado estado com o id cadastrado.");
        }
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public void delete(Long id){
        try{
           cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new ValidateMessageException("Cidade não pode ser removida, pois está em uso.");
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFound("Cidade não encontrada!");
        }
    }
}
