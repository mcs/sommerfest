package de.silpion.sommerfest.service;

import de.silpion.sommerfest.ejb.LoginFascade;
import de.silpion.sommerfest.ejb.RegisterUserFascade;
import de.silpion.sommerfest.exception.RegisterUserException;
import de.silpion.sommerfest.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private LoginFascade mockLoginFascade;
    @Mock
    private RegisterUserFascade mockRegisterUserFascade;
    @InjectMocks
    private UserService userService = new UserService();

    @Test
    public void correctCredentialsReturnValidUser() throws Exception {
        User expected = new User();
        expected.setUsername("uname");
        when(mockLoginFascade.login(anyString(), anyString())).thenReturn(expected);

        User result = userService.login("user", "pass");

        assertThat(result, is(expected));
    }

    @Test
    public void wrongCredentialsReturnUserWithEmptyUsername() throws Exception {
        User result = userService.login("user", "pass");

        assertThat(result.getUsername(), is(nullValue()));
    }

    @Test
    public void shouldRegisterNewUser() {
        User expected = new User();
        String username = "newuser";
        expected.setUsername(username);
        when(mockRegisterUserFascade.register(eq(username), anyString())).thenReturn(expected);

        User result = userService.register(username, "pass");

        assertThat(result, is(expected));
    }

    @Test
    public void shouldntRegisterExistingUser() {
        when(mockRegisterUserFascade.register("existinguser", "pass")).thenThrow(RegisterUserException.class);

        User result = userService.register("existinguser", "pass");

        assertThat(result.getUsername(), is(nullValue()));
    }
}
