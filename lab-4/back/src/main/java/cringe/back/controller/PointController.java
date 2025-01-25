package cringe.back.controller;

import cringe.back.entity.Point;
import cringe.back.entity.User;
import cringe.back.exceptions.InvalidPasswordException;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.ServiceManager;
import cringe.back.service.ServiceName;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("point")
public class PointController {

    @EJB
    private ServiceManager serviceManager;


    @POST
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPoints(User user) {
        try {
            serviceManager.execute(ServiceName.AUTHENTICATION, user);
            String points = serviceManager.execute(ServiceName.GET_POINTS, user);
            return Response.ok(points).build();
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
    public Response addUserPoint(Point point, User user) {
        try {
            serviceManager.execute(ServiceName.AUTHENTICATION, user);
            serviceManager.execute(ServiceName.ADD_POINT, point);
            return Response.ok("done").build();
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
    public Response deleteUserPoints(User user) {
        try {
            serviceManager.execute(ServiceName.AUTHENTICATION, user);
            serviceManager.execute(ServiceName.DELETE_POINTS, user);
            return Response.ok("done").build();
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
