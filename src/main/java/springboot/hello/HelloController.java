package springboot.hello;

public class HelloController {

    public String hello(String message) {
        return "Hello " + message;
    }

}
