package de.silpion.sommerfest.rest;

import de.silpion.sommerfest.model.Order;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("/orders")
public class OrderService {

    private static long NEXT_ORDER_ID = 1;

    private static Map<String, List<Order>> repository = new HashMap<String, List<Order>>();

    @GET
    @Path("{target}")
    @Produces(APPLICATION_JSON)
    public List<Order> query(@PathParam("target") String target) {
        if (!repository.containsKey(target)) {
            repository.put(target, new ArrayList<Order>());
        }
        return repository.get(target);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Order save(Order order) {
        String target = order.getTarget();
        if (!repository.containsKey(target)) {
            repository.put(target, new ArrayList<Order>());
        }
        order = persist(order);
        return order;
    }

    @DELETE
    @Path("{target}")
    @Produces(APPLICATION_JSON)
    public Order delete(@PathParam("target") long orderId) {
        Order deleted = null;
        for (List<Order> orders : repository.values()) {
            Iterator<Order> it = orders.iterator();
            while (it.hasNext()) {
                Order order = it.next();
                if (order.getId() == orderId) {
                    deleted = order;
                    it.remove();
                }
            }
        }
        return deleted;
    }

    private Order persist(Order order) {
        repository.get(order.getTarget()).add(order);
        order.setId(NEXT_ORDER_ID++);
        return order;
    }

}
