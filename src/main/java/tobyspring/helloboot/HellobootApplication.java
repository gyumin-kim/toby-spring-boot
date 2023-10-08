package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// @Configuration을 갖는 클래스는 AnnotationConfig 기반의 ApplicationContext에 처음으로 등록됨
// @Configuration: @Bean 설정 정보가 포함된 클래스임을 Spring container에 알리기 위함
@Configuration
public class HellobootApplication {
	@Bean
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService);
	}

	@Bean
	public HelloService helloService() {
		return new SimpleHelloService();
	}

	public static void main(String[] args) {
		// Spring container
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();
				// Servlet container
				TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
				WebServer webServer = tomcatServletWebServerFactory.getWebServer(
					servletContext -> servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
													.addMapping("/*")    // front controller url mapping
				);
				webServer.start();
			}
		};
		applicationContext.register(HellobootApplication.class);	// 구성 정보를 갖는 Java 클래스 등록
		applicationContext.refresh();	// ApplicationContext(Spring container) 초기화, bean object 생성
	}

}
