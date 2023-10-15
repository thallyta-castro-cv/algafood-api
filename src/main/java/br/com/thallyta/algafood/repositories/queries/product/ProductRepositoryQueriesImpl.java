package br.com.thallyta.algafood.repositories.queries.product;

import br.com.thallyta.algafood.models.ProductPhoto;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProductRepositoryQueriesImpl implements ProductRepositoryQueries{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ProductPhoto save(ProductPhoto photo) {
        return entityManager.merge(photo);
    }

    @Override
    @Transactional
    public void delete(ProductPhoto photo) {
        entityManager.remove(photo);
    }
}
