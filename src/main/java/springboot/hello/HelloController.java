package springboot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController // @Controller + @ResponseBody --> 합성 애노테이션
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        if (name == null) {
            throw new NullPointerException();
        }

        if (name.trim().length() == 0) {
            throw new IllegalStateException();
        }
        return helloService.sayService(name);
    }

}
