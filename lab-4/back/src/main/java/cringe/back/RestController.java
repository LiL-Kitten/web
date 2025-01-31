package cringe.back;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@ApplicationPath("/app")
public class RestController {

    @GET
    @Path("helo")
    public String helo() {
        return "Hello World!";
    }
}
