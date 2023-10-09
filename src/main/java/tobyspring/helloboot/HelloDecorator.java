package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary    // HelloService에 의존하는 곳에서 HelloService 타입의 bean 주입 시 primary bean을 우선적으로 선택
public class HelloDecorator implements HelloService {
    private final HelloService helloService;

    public HelloDecorator(final HelloService helloService) {    // HelloDecorator 타입의 bean 후보 중 자기 자신(HelloDecorator)은 제외
        this.helloService = helloService;
    }

    @Override
    public String sayHello(final String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
