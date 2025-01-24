package cringe.backend.controller;

import cringe.backend.entity.User;
import cringe.backend.service.AuthService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("authorization")
public class AuthController {

    @EJB
    private AuthService authService;

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
            String message = authService.registerUser(user.getUsername(), user.getPassword());
            return Response.ok(message).build();
    }

    @POST
    @Path("authorize")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorize(User user) {
            String message = authService.authenticateUser(user.getUsername(), user.getPassword());
            return Response.ok(message).build();
    }
}

