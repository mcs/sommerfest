package de.silpion.sommerfest.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UserTest {

    static final String TESTPW = "testpw";
    static final String TESTHASH = "$2a$10$LfPBwzQUsui.gnH9boD7t.1PzCRj85L2tNY79duMGZZz5hBchQYd6";

    User user;

    @Before
    public void setup() {
        user = new User();
    }

    @Test
    public void shouldHashPassword() {
        user.setNewPassword(TESTPW);

        assertThat(user.getPasshash(), is(notNullValue()));
        assertThat(user.getPasshash(), is(not(equalTo(TESTPW))));
    }

    @Test
    public void shouldAcceptCorrectPassword() {
        user.setPasshash(TESTHASH);

        assertThat(user.isPasswordCorrect(TESTPW), is(true));
    }

    @Test
    public void shouldRejectWrongPassword() {
        user.setPasshash(TESTHASH);

        assertThat(user.isPasswordCorrect("wrongpw"), is(false));
    }

}
