package cringe.back.controller;

import cringe.back.dto.PointDTO;
import cringe.back.dto.UserDTO;
import cringe.back.exceptions.InvalidPasswordException;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.ServiceName;
import cringe.back.service.ServiceResponse;
import cringe.back.service.UserServiceFactory;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("point")
public class PointController {

    @EJB
    UserServiceFactory userServiceFactory;

    @POST
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPoints(UserDTO user) {
        try {
            ServiceResponse<?> response = userServiceFactory.createService(ServiceName.GET_POINTS).execute(user);

            return Response.ok(response).build();
        } catch (InvalidPasswordException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserPoint(PointDTO point, UserDTO user) {
        try {


            return Response.ok(point.getY() + user.getUsername()).build();
        } catch (InvalidPasswordException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred").build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserPoints(UserDTO user) {
        try {
            ServiceResponse<?> response = userServiceFactory.createService(ServiceName.DELETE_POINTS).execute(user);

            return Response.ok(response).build();
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
