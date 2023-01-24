package springboot.hello;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloApplication {

    public static void main(String[] args) {
        // 톰캣 사용(추상화가 잘 되어있어서 톰캣 대신 jetty로 바꿀 수도 있음)
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // 서블릿 컨테이너 생성
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            // 컨트롤러 추가
            HelloController helloController = new HelloController();

            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통 기능 등 있다고 가정
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String msg = req.getParameter("message");

                        // 서블릿의 비지니스 로직
                        String ret = helloController.hello(msg);

                        // 상태 라인, 상태 코드
//                    resp.setStatus(200);
                        resp.setStatus(HttpStatus.OK.value());
                        // 헤더(특히 Content-Type 헤더)
                        // resp.setHeader("Content-Type", "text/plain"); -> 오타 위험성
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        // 바디
                        resp.getWriter().println(ret);
                    } else if (req.getRequestURI().equals("/member")) {

                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start();
    }

}
