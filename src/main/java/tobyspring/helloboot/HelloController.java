package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

// DispatcherServlet이 url 매핑 정보를 만들 때, 1. class level, 2. method level의 순서로 참고하므로,
// @GetMapping과는 별도로 클래스에도 선언 필요
@RequestMapping
public class HelloController {
    private final HelloService helloService;

    public HelloController(final HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(final String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
