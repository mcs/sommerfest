package de.silpion.sommerfest.model;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "findUserByUsername",
                query = "SELECT u FROM User u WHERE u.username = :username")
})
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

    @Id
    private String id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    @XmlTransient
    private String passhash;
    private String sessionId;

    public boolean isPasswordCorrect(String password) {
        return BCrypt.checkpw(password, passhash);
    }

    public void setNewPassword(String password) {
        this.passhash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasshash() {
        return passhash;
    }

    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
