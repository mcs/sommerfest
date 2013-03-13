package de.silpion.sommerfest.rest;

import de.silpion.sommerfest.model.Product;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/products")
public class ProductService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> query() {
        // TODO Fetch from repository
        List<Product> products = new ArrayList<Product>();
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setName("Product #" + i);
            products.add(product);
        }
        return products;
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product get(@PathParam("productId") Long id) {
        // TODO Fetch from repository
        Product product = new Product();
        product.setId(id);
        product.setName("Product #" + id);
        return product;
    }
}
