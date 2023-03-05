package springboot.hello;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HelloControllerTest {

    HelloController helloController;

    @BeforeEach
    @Test
    void setUp() {
        helloController = new HelloController(name -> name);
    }

    @Test
    @DisplayName("/hello 성공")
    void helloController() {
        String res = helloController.hello("start");

        assertThat(res).isEqualTo("start");
    }

    @Test
    @DisplayName("/hello 실패 -> NPE")
    void failHelloController_Null() {
        assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("/hello 실패 -> Illegal")
    void failHelloController_빈칸() {
        assertThatThrownBy(() -> {
            helloController.hello("       ");
        }).isInstanceOf(IllegalStateException.class);
    }

}