package de.silpion.sommerfest.ejb;

import de.silpion.sommerfest.model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderBean {

    @PersistenceContext
    EntityManager em;

    public List<Order> findByTarget(String target) {
        return em
                .createNamedQuery("Order.findByTarget", Order.class)
                .setParameter("target", target)
                .getResultList();
    }

    public List<Order> findAll() {
        return em
                .createNamedQuery("Order.findAll", Order.class)
                .getResultList();
    }

    public Order deleteById(long orderId) {
        Order orderToDelete = em.find(Order.class, orderId);
        em.remove(orderToDelete);
        return orderToDelete;
    }

    public Order save(Order order) {
        em.persist(order);
        return order;
    }
}
