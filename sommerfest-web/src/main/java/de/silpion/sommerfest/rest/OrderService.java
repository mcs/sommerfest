package de.silpion.sommerfest.rest;

import de.silpion.sommerfest.ejb.OrderBean;
import de.silpion.sommerfest.model.Order;
import de.silpion.sommerfest.model.ProcessState;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path("/orders")
public class OrderService {

    @EJB
    private OrderBean orderBean;

    @GET
    @Path("{target}")
    @Produces(APPLICATION_JSON)
    public List<Order> query(@PathParam("target") String target) {
        return orderBean.findByTarget(target);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<Order> query(@QueryParam("state") ProcessState state) {
        return orderBean.findByState(state);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Order save(Order order) {
        return orderBean.save(order);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Order update(Order order) {
        return orderBean.update(order);
    }

    @DELETE
    @Path("{orderId}")
    @Produces(APPLICATION_JSON)
    public Response delete(@PathParam("orderId") long orderId) {
        try {
            orderBean.deleteById(orderId);
            return Response.ok().build();
        } catch (RuntimeException e) {
            return Response.notModified().build();
        }
    }

}
