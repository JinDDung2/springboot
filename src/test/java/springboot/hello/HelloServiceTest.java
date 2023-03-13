package springboot.hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest // UnitTest target에 애노테이션 타입이 있기에 가능
@interface FastUnitTest {}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD}) // target에 여러타입이 오기 위해 배열 선언
@Test
@interface UnitTest {}

public class HelloServiceTest {
    @UnitTest
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
