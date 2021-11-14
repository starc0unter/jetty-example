package jetty.example;

import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("NotNullNullableValidation")
public final class ServletHttp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        final var id = req.getParameter("id");
        if (id == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }
        final var cacheControl = req.getHeader("Cache-control");
        try (final PrintWriter out = resp.getWriter()) {
            out.println("Data id = " + id + ", cache-control = " + cacheControl);
        }
    }



}
