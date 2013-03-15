package de.silpion.sommerfest.ejb;

import de.silpion.sommerfest.model.Order;
import de.silpion.sommerfest.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderBean {

    @PersistenceContext
    EntityManager em;

    public List<Order> findAll(String target) {
        return em
                .createQuery("SELECT o FROM Order o WHERE o.target = :target", Order.class)
                .setParameter("target", target)
                .getResultList();
    }

    public List<Order> findAll() {
        return em
                .createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    public void deleteById(long productId) {
        Product orderToDelete = em.find(Product.class, productId);
        em.remove(orderToDelete);
    }

    public Order save(Order order) {
        em.persist(order);
        return order;
    }
}
