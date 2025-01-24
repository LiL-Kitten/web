package cringe.backend.controller;

import cringe.backend.entity.Point;
import cringe.backend.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Path("user")
public class UserController {

    @EJB
    private UserService userService;

    @Context
    private SecurityContext securityContext;

    @POST
    @Path("/points")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPoints() {
        UserPrincipal userPrincipal = (UserPrincipal) securityContext.getUserPrincipal();
        List<Point> points = null;
        try {
            points = userService.getUserPoints(Long.valueOf(userPrincipal.getName()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Response.ok(points).build();
    }

    @POST
    @Path("/addPoint")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserPoint(Point point) {
        UserPrincipal userPrincipal = (UserPrincipal) securityContext.getUserPrincipal();
        try {
            userService.addUserPoint(Long.valueOf(userPrincipal.getName()), point);
            return Response.ok(point).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deleteAllPoints")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserPoints() {
        UserPrincipal userPrincipal = (UserPrincipal) securityContext.getUserPrincipal();
        try {
            Long userId = Long.valueOf(userPrincipal.getName());
            userService.deleteUserPoints(userId);
            return Response.ok().entity("Все точки успешно удалены.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Внутренняя ошибка сервера").build();
        }
    }
}
