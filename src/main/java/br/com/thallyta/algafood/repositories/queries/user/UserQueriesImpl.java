package br.com.thallyta.algafood.repositories.queries.user;

import br.com.thallyta.algafood.models.UserSystem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class UserQueriesImpl implements UserQueries{

    @Autowired
    private EntityManager manager;

    @Override
    public void detachUser(UserSystem user) {
        manager.detach(user);
    }
}
