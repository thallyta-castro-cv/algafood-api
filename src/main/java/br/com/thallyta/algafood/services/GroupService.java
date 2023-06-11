package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.core.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Group;
import br.com.thallyta.algafood.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public Group save(Group kitchen) {
        return groupRepository.save(kitchen);
    }

    @Transactional
    public void delete(Long id){
        try{
            groupRepository.deleteById(id);
            groupRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new EntityExceptionInUse("Grupo não pode ser removido, pois está em uso.");
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Grupo não encontrado!");
        }
    }

    public Group findOrFail(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Grupo não encontrado!"));
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }
}
