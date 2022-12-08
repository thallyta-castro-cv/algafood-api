package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> getAll();
    Permission getById(Long id);
    Permission save(Permission permission);
    void delete(Permission permission);

}
