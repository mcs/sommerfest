package de.silpion.sommerfest.ejb;

import de.silpion.sommerfest.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductBeanTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private ProductBean productBean = new ProductBean();

    @Test
    public void shouldFindAllProducts() {
        List<Product> expected = new ArrayList<Product>();
        TypedQuery mockTypedQuery = mock(TypedQuery.class);
        when(em.createNamedQuery("Product.findAll", Product.class)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(expected);

        List<Product> result = productBean.findAll();

        assertThat(result, is(sameInstance(expected)));
    }

    @Test
    public void shouldDeleteById() {
        Product productToDelete = new Product();
        when(em.find(Product.class, 42L)).thenReturn(productToDelete);

        productBean.deleteById(42L);

        verify(em).remove(productToDelete);
    }

    @Test
    public void shouldSaveNewProduct() {
        Product product = new Product();
        product.setName("ProdName");

        Product result = productBean.save(product);

        assertThat(result, is(product));
        verify(em).persist(product);
    }
}
