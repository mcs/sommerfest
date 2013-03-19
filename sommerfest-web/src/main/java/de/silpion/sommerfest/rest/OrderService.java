package de.silpion.sommerfest.rest;

import de.silpion.sommerfest.ejb.OrderBean;
import de.silpion.sommerfest.model.Order;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import java.util.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path("/orders")
public class OrderService {

    @EJB
    private OrderBean orderBean;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Order> query() {
        return orderBean.findAll();
    }

    @GET
    @Path("{target}")
    @Produces(APPLICATION_JSON)
    public List<Order> query(@PathParam("target") String target) {
        return orderBean.findByTarget(target);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Order save(Order order) {
        return orderBean.save(order);
    }

    @DELETE
    @Path("{orderId}")
    @Produces(APPLICATION_JSON)
    public Order delete(@PathParam("orderId") long orderId) {
        return orderBean.deleteById(orderId);
    }

}
