package de.silpion.sommerfest.rest;

import de.silpion.sommerfest.ejb.ProductBean;
import de.silpion.sommerfest.model.Product;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path("/products")
public class ProductService {

    @EJB
    private ProductBean productBean;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Product> query() {
        return productBean.findAll();
    }

    @DELETE
    @Path("{productId}")
    @Produces(APPLICATION_JSON)
    public Response delete(@PathParam("productId") long productId) {
        try {
            productBean.deleteById(productId);
            return Response.ok().build();
        } catch (EJBException e) {
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Product save(Product product) {
        return productBean.save(product);
    }

}
