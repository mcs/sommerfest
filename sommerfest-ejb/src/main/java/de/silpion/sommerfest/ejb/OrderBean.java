package de.silpion.sommerfest.ejb;

import de.silpion.sommerfest.model.Order;
import de.silpion.sommerfest.model.ProcessState;

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
                .setParameter("notState", ProcessState.RECEIVED)
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

    public List<Order> findByState(ProcessState status) {
        return em.createNamedQuery("Order.findByStatus", Order.class)
                .setParameter("status", status)
                .getResultList();
    }

    public Order update(Order order) {
        return em.merge(order);
    }
}
