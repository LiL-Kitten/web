package cringe.lab3.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import cringe.lab3.bean.Data;
import cringe.lab3.bean.DataList;
import cringe.lab3.util.Parser;
import cringe.lab3.util.ParsingException;

import java.io.IOException;
import java.util.Set;

public class AreaCheckServlet extends HttpServlet {

    private static final Parser PARSER = new Parser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String values = (String) req.getAttribute("values");

        if (values == null || values.isEmpty()) {
            resp.setStatus(400);
            return;
        }

        try {
            Data data = PARSER.parse(values);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            jakarta.validation.Validator validator = factory.getValidator();

            Set<ConstraintViolation<Data>> violations = validator.validate(data);

            if (!violations.isEmpty()) {
                resp.setStatus(400);
                return;
            }

            DataList list = (DataList) req.getSession().getAttribute("list");
            if (list == null) {
                list = new DataList();
            }

            list.addData(data);
            req.getSession().setAttribute("list", list);

            resp.sendRedirect("/app/result.jsp");
        } catch (ParsingException e) {
            resp.setStatus(400);
        } catch (IOException e) {
            resp.setStatus(500);
            getServletContext().log("Error parsing data", e);
        }
    }
}
