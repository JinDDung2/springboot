package springboot.hello;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
    @Test
    @DisplayName("hello api 테스트")
    void helloApi() {
        // http localhost:8080/hello?name=start
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> res =
                restTemplate.getForEntity("http://localhost:8080/hello?name={name}", String.class, "start");

        // status 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type) -> text/plain
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body -> start service complete
        assertThat(res.getBody()).isEqualTo("start service complete");
    }

    @Test
    @DisplayName("hello api 테스트 실패")
    void failHelloApi() {
        // http localhost:8080/hello?name=start
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> res =
                restTemplate.getForEntity("http://localhost:8080/hello?na=", String.class);

        // status 500 -> 원래는 4xx에러 발생해야함
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
