package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(HelloController.class);	// bean 설정 정보 등록
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();	// ApplicationContext(Spring container) 초기화, bean object 생성

		TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext ->
			servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
						  .addMapping("/*")	// front controller url mapping
		);
		webServer.start();
	}

}
