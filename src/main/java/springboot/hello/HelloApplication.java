package springboot.hello;

import org.springframework.boot.SpringApplication;

@MySpringBootAnnotation
public class HelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }
}
