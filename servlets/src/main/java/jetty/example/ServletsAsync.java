package jetty.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@SuppressWarnings({"Duplicates", "NotNullNullableValidation"})
public final class ServletsAsync {

  public static void main(String[] args) throws Exception {
    final Server server = new DefaultServer().build();

    ServletContextHandler context = new ServletContextHandler(
        ServletContextHandler.NO_SESSIONS
    );
    context.setContextPath("/");
    context.addServlet(
        new ServletHolder("async", new AsyncServlet()),
        "/"
    );
    context.addServlet(
            new ServletHolder("classicGet", new ServletHttp()),
            "/get"
    );
    server.setHandler(context);

    server.start();
  }
}
