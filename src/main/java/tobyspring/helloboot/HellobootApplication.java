package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);	// bean 설정 정보 등록
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();	// ApplicationContext(Spring container) 초기화, bean object 생성

		TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
			HelloController helloController = applicationContext.getBean(HelloController.class);
			servletContext.addServlet("frontcontroller", new HttpServlet() {
							  @Override
							  protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
								  // 인증, 보안, 다국어 공통 기능 등
								  if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
									  String name = req.getParameter("name");
									  String ret = helloController.hello(name);
									  resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
									  resp.getWriter().println(ret);
								  } else {
									  resp.setStatus(HttpStatus.NOT_FOUND.value());
								  }
							  }
						  })
						  .addMapping("/*");	// front controller url mapping
		});
		webServer.start();
	}

}
