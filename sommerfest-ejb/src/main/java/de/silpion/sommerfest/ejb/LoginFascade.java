package de.silpion.sommerfest.ejb;

import de.silpion.sommerfest.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Stateless
public class LoginFascade {

    @PersistenceContext
    EntityManager em;

    public User login(@NotNull String username, @NotNull String password) {
        User user = findUserByUsername(username);
        if (credentialsCorrect(user, password)) {
            String sessionId = UUID.randomUUID().toString();
            user.setSessionId(sessionId);
            return user;
        }
        return null;
    }

    private User findUserByUsername(String username) {
        TypedQuery<User> query = em.createNamedQuery("findUserByUsername", User.class);
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    private boolean credentialsCorrect(User user, String password) {
        return user != null;
    }

}
