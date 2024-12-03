package cringe.lab3.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String values = request.getQueryString();
        try {
            if (values != null && !values.isEmpty()) {
                request.setAttribute("values", values);
                request.setAttribute("fromController", "true");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/checker");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            response.setStatus(500);
        }
    }
}
