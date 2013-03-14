package de.silpion.sommerfest.ejb;

import de.silpion.sommerfest.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginFascadeTest {

    @Mock
    private EntityManager em;
    @Mock
    private TypedQuery<User> query;
    @InjectMocks
    private LoginFascade loginFascade = new LoginFascade();

    @Test
    public void testSuccessfulLogin() throws Exception {
        String username = "user";
        String password = "pass";
        when(em.createNamedQuery(any(String.class), eq(User.class))).thenReturn(query);
        User expected = new User();
        expected.setUsername(username);
        expected.setNewPassword(password);
        when(query.getResultList()).thenReturn(Arrays.asList(expected));

        User result = loginFascade.login(username, password);

        assertThat(result, is(expected));

        verify(query).setParameter("username", username);
    }

    @Test
    public void testWrongUser() throws Exception {
        String password = "pass";
        when(em.createNamedQuery(any(String.class), eq(User.class))).thenReturn(query);
        User expected = new User();
        expected.setNewPassword(password);
        when(query.getSingleResult()).thenReturn(null);

        User result = loginFascade.login("wrong", password);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void testWrongPassword() throws Exception {
        String username = "user";
        String password = "pass";
        when(em.createNamedQuery(any(String.class), eq(User.class))).thenReturn(query);
        User expected = new User();
        expected.setNewPassword(password);
        when(query.getSingleResult()).thenReturn(expected);

        User result = loginFascade.login(username, "wrongPass");

        assertThat(result, is(nullValue()));
    }

}
