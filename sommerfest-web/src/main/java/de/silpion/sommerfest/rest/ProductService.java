package de.silpion.sommerfest.rest;

import de.silpion.sommerfest.ejb.ProductBean;
import de.silpion.sommerfest.model.Product;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path("/products")
public class ProductService {

    private static long NEXT_PRODUCT_ID = 1;
    private static List<Product> products = new ArrayList<Product>();

    static {
        Product product = new Product();
        product.setId(1L);
        product.setName("Cocktailgläser");
        products.add(product);

        product = new Product();
        product.setId(2L);
        product.setName("Havanna Club Rum");
        products.add(product);
    }

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
    public Product delete(@PathParam("productId") long productId) {
        productBean.deleteById(productId);
        // TODO Fetch from repository
        Iterator<Product> it = products.iterator();
        while (it.hasNext()) {
            Product product = it.next();
            if (product.getId() == productId) {
                it.remove();
                return product;
            }
        }
        return null;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Product save(Product product) {
        return productBean.save(product);
    }

    private Product persist(Product product) {
        products.add(product);
        product.setId(NEXT_PRODUCT_ID++);
        return product;
    }
}
