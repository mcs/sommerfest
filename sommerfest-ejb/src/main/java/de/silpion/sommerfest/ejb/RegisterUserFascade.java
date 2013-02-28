package de.silpion.sommerfest.ejb;

import de.silpion.sommerfest.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

@Stateless
public class RegisterUserFascade {

    @PersistenceContext
    EntityManager em;

    public User register(@NotNull String username, @NotNull String password) {
        return null;
    }

}
