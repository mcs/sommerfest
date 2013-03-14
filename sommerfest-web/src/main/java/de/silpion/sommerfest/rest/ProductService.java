package de.silpion.sommerfest.rest;

import de.silpion.sommerfest.model.Product;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("/products")
public class ProductService {

    @GET
    @Produces(APPLICATION_JSON)
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
    @Produces(APPLICATION_JSON)
    public Product get(@PathParam("productId") Long id) {
        // TODO Fetch from repository
        Product product = new Product();
        product.setId(id);
        product.setName("Product #" + id);
        return product;
    }
}
