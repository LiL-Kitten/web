package cringe.back.controller;

import cringe.back.dto.PointDTO;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.UserServiceFactory;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("point")
public class PointController {

    @Context
    private SecurityContext securityContext;

    @EJB
    private UserServiceFactory userServiceFactory;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPoints() {
        try {
            Long userId = getUserIdFromContext();
            List<PointDTO> list = userServiceFactory.getPoints(userId);
            return Response.ok(list).build();
        } catch (Exception e) {
            return handleExceptions(e);
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserPoint(PointDTO point) {
        try {
            Long userId = getUserIdFromContext();
            String response = userServiceFactory.addPoints(userId, point);
            return Response.ok(response).build();
        } catch (Exception e) {
            return handleExceptions(e);
        }
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserPoints() {
        try {
            Long userId = getUserIdFromContext();
            String response = userServiceFactory.delPoints(userId);
            return Response.ok(response).build();
        } catch (Exception e) {
            return handleExceptions(e);
        }
    }

    private Long getUserIdFromContext() {
        if (securityContext.getUserPrincipal() == null) {
            throw new SecurityException("User not authenticated");
        }
        return Long.parseLong(securityContext.getUserPrincipal().getName());
    }

    private Response handleExceptions(Exception e) {
        if (e instanceof UserNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        if (e instanceof SecurityException) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\":\"" + e.getMessage() + "\"}").build();
    }
}