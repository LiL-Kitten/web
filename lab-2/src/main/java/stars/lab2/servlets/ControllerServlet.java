package stars.lab2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String values = request.getQueryString();

            if (values != null && !values.isEmpty()) {
                request.setAttribute("values", values);

                request.getRequestDispatcher("/checker").forward(request, response);
            } else {
                response.sendRedirect( "/index.jsp");
            }
        } catch (ServletException | IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
