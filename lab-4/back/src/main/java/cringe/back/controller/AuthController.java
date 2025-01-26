package cringe.back.controller;

import cringe.back.dto.UserDTO;
import cringe.back.exceptions.InvalidPasswordException;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.AuthServiceFactory;
import cringe.back.service.ServiceName;
import cringe.back.service.ServiceResponse;
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
    private AuthServiceFactory authServiceFactory;

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserDTO user) {
        ServiceResponse<?> response = authServiceFactory.createService(ServiceName.REGISTRATION).execute(user);

        return Response.ok(response).build();
    }

    @POST
    @Path("authorize")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorize(UserDTO user) {
        try {
            authServiceFactory.createService(ServiceName.AUTHENTICATION).execute(user);
            return Response.ok().build();
        } catch (InvalidPasswordException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred").build();
        }
    }
}

