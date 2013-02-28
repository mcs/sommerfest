package de.silpion.sommerfest.service;

import de.silpion.sommerfest.ejb.LoginFascade;
import de.silpion.sommerfest.ejb.RegisterUserFascade;
import de.silpion.sommerfest.exception.RegisterUserException;
import de.silpion.sommerfest.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Named
@RequestScoped
@Path("/user")
public class UserService {

    @EJB
    private LoginFascade loginFascade;

    @EJB
    private RegisterUserFascade registerUserFascade;

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public User login(
            @QueryParam("username") String username,
            @QueryParam("password") String password) {
        User user = loginFascade.login(username, password);
        return user == null ? new User() : user;
    }

    @PUT
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public User register(
            @QueryParam("username") String username,
            @QueryParam("password") String password) {
        try {
            return registerUserFascade.register(username, password);
        } catch (RegisterUserException e) {
            return new User();
        }
    }
}
