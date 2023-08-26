package br.com.thallyta.algafood.repositories.queries.user;

import br.com.thallyta.algafood.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class UserQueriesImpl implements UserQueries{

    @Autowired
    private EntityManager manager;

    @Override
    public void detachUser(User user) {
        manager.detach(user);
    }
}
