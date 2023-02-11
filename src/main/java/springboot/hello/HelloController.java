package springboot.hello;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {

        return helloService.sayService(Objects.requireNonNull(name));
    }

}
