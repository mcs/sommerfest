package de.silpion.sommerfest.service;

import de.silpion.sommerfest.ejb.LoginFascade;
import de.silpion.sommerfest.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private LoginFascade mockLoginFascade;
    @InjectMocks
    private UserService loginService = new UserService();

    @Test
    public void shouldReurnUserForCorrectCredentials() {
        User expected = new User();
        String username = "user";
        String password = "pass";
        when(mockLoginFascade.login(username, password)).thenReturn(expected);

        User result = loginService.login(username, password);

        assertThat(result, is(expected));
    }

    @Test
    public void shouldReturnNullForInvalidCredentials() {
        User result = loginService.login("x", "y");

        assertThat(result.getUsername(), is(nullValue()));
    }
}
