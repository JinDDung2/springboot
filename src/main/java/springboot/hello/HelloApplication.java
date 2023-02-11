package springboot.hello;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
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

        GenericApplicationContext applicationContext = new GenericApplicationContext();
        // 스프링 컨테이너에 빈 등록
        applicationContext.registerBean(HelloController.class);
        // 초기화하기
        applicationContext.refresh();

        // 톰캣 사용(추상화가 잘 되어있어서 톰캣 대신 jetty로 바꿀 수도 있음)
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // 서블릿 컨테이너 생성
        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통 기능 등 있다고 가정
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String msg = req.getParameter("message");

                        // 빈의 이름을 가져오는 방식 대신 클래스 빈의 타입이 1개만 있기에 클래스로 가져옴
                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String ret = helloController.hello(msg);

                        // 헤더(특히 Content-Type 헤더)
                        // resp.setHeader("Content-Type", "text/plain"); -> 오타 위험성
                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        // 바디
                        resp.getWriter().println(ret);
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start();
    }

}
