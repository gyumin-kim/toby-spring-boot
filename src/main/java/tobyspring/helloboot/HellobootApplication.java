package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
							  @Override
							  protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
								  resp.setStatus(200);
								  resp.setHeader("Content-Type", "text/plain");
								  resp.getWriter().println("Hello Servlet");
							  }
						  })
						  .addMapping("/hello");	// url mapping
		});
		webServer.start();
	}

}
