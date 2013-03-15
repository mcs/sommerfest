package de.silpion.sommerfest.ejb;

import de.silpion.sommerfest.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductBean {

    @PersistenceContext
    EntityManager em;

    public List<Product> findAll() {
        return em
                .createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }

    public void deleteById(long productId) {
        Product productToDelete = em.find(Product.class, productId);
        em.remove(productToDelete);
    }

    public Product save(Product product) {
        em.persist(product);
        return product;
    }
}
