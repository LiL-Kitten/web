package cringe.back.controller;

import cringe.back.dto.UserDTO;
import cringe.back.exceptions.InvalidPasswordException;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.AuthServiceFactory;
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
    private AuthServiceFactory serviceFactory;

    @POST
    @Path("reg")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserDTO user) {
        try {
            String response = serviceFactory.registration(user);
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorize(UserDTO user) {
        try {
            String response = serviceFactory.authenticate(user);
            return Response.ok(response).build();
        } catch (InvalidPasswordException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }
}