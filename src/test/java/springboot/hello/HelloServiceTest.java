package springboot.hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService();
        String ret = simpleHelloService.sayService("ret");
        Assertions.assertThat(ret).isEqualTo("ret service complete");
    }

    @Test
    void helloDeco() {
        HelloDeco deco = new HelloDeco(name -> name);
        String ret = deco.sayService("ret");
        Assertions.assertThat(ret).isEqualTo("??? ret");
    }
}
