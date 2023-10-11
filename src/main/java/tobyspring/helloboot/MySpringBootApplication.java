package tobyspring.helloboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan
// @Configuration을 갖는 클래스는 AnnotationConfig 기반의 ApplicationContext에 처음으로 등록됨
// @Configuration: @Bean 설정 정보가 포함된 클래스임을 Spring container에 알리기 위함
@Configuration
public @interface MySpringBootApplication {
}
