package jetty.example;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("NotNullNullableValidation")
public final class LoginHash {

  public static void main(String[] args) throws Exception {
    final Server server = new DefaultServer().build();

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    context.setContextPath("/");
    final URL resource = LoginService.class.getResource("/static");
    context.setBaseResource(Resource.newResource(resource.toExternalForm()));
    context.setWelcomeFiles(new String[]{"/static/second/example"});
    context.addServlet(new ServletHolder("default", DefaultServlet.class), "/*");

    final String hashConfig = LoginHash.class.getResource("/hash_config").toExternalForm();
    final HashLoginService hashLoginService = new HashLoginService("login", hashConfig);
    final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(hashLoginService);
    server.addBean(hashLoginService);
    securityHandler.setHandler(context);
    server.setHandler(securityHandler);

    server.start();
  }
}
