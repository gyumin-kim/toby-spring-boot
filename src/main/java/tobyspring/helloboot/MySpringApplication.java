package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(final Class<?> applicationClass, final String[] args) {
        // Spring container
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                // Servlet container
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                // DispatcherServlet은 ApplicationContextAware의 구현체이므로, setApplicationContext()를 통해 ApplicationContext를 주입 받음
                WebServer webServer = serverFactory.getWebServer(
                    servletContext -> servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                                                    .addMapping("/*")    // front controller url mapping
                );
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);	// 구성 정보를 갖는 Java 클래스 등록
        applicationContext.refresh();	// ApplicationContext(Spring container) 초기화, bean object 생성
    }
}
