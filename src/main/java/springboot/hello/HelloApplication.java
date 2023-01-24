package springboot.hello;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
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
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    String msg = req.getParameter("message");

                    // 상태 라인, 상태 코드
//                    resp.setStatus(200);
                    resp.setStatus(HttpStatus.OK.value());
                    // 헤더(특히 Content-Type 헤더)
                    // resp.setHeader("Content-Type", "text/plain"); -> 오타 위험성
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    // 바디
                    resp.getWriter().println("Welcome Servlet" + msg);
                }
            }).addMapping("/hello");
        });
        webServer.start();
    }

}
