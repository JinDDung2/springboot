package springboot.hello;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class HelloDeco implements HelloService{

    private final HelloService helloService;

    public HelloDeco(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayService(String name) {
        return "??? " + helloService.sayService(name);
    }
}
