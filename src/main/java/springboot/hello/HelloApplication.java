package springboot.hello;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HelloApplication {

    public static void main(String[] args) {

        // DispatcherServlet 을 사용하기위해 GenericApplicationContext -> GenericWebApplicationContext
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        // 스프링 컨테이너에 빈 등록
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        // 초기화하기
        applicationContext.refresh();

        // 톰캣 사용(추상화가 잘 되어있어서 톰캣 대신 jetty로 바꿀 수도 있음)
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // 서블릿 컨테이너 생성
        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            servletContext.addServlet("dispatcherServlet",
                    new DispatcherServlet(applicationContext)
                    ).addMapping("/*");
        });
        webServer.start();
    }

}
