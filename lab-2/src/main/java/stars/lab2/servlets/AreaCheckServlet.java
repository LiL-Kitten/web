package stars.lab2.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stars.lab2.bean.Data;
import stars.lab2.bean.DataList;
import stars.lab2.util.Parser;
import stars.lab2.util.ParsingException;

import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {

    @EJB
    private DataList list;

    private static final Parser PARSER = new Parser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String values = req.getParameter("values");

        if (values != null && !values.isEmpty()) {
            try {
                Data data = PARSER.parse(values);

                list.addData(data);
                req.getSession().setAttribute("list", list);

                resp.sendRedirect(req.getContextPath() + "/result.jsp");

            } catch (ParsingException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        resp.sendRedirect("index.jsp");
    }


}


