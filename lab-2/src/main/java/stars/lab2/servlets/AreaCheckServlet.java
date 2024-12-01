package stars.lab2.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stars.lab2.bean.Data;
import stars.lab2.bean.DataList;
import stars.lab2.util.Parser;
import stars.lab2.util.ParsingException;

import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {

    private final DataList list;
    private static final Parser PARSER = new Parser();

    // Конструктор с инъекцией зависимостей
    @Inject
    public AreaCheckServlet(DataList list) {
        this.list = list;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String values = (String) req.getAttribute("values");

        if (values == null || values.isEmpty()) {
            resp.setStatus(400);
            return;
        }

        try {
            Data data = PARSER.parse(values);
            list.addData(data);

            resp.sendRedirect("/app/result.jsp");
        } catch (ParsingException e) {
            resp.setStatus(400);
        } catch (IOException e) {
            resp.setStatus(500);
            getServletContext().log("Error parsing data", e);
        }
    }
}
