package springboot.hello;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApp {
    public static void run(Class<?> applicationClass, String[] args) {
        // DispatcherServlet 을 사용하기위해 GenericApplicationContext -> GenericWebApplicationContext
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                // DispatcherServlet에서 ApplicationContextAware 인터페이스에서 자동으로 application을 인지해서 아래 코드 한줄을 생략 가능
                dispatcherServlet.setApplicationContext(this);
                // 서블릿 컨테이너 생성
                WebServer webServer = serverFactory.getWebServer(servletContext -> {

                    servletContext.addServlet("dispatcherServlet",
                            // 기존에는 ApplicationContext 객체를 생성해서 변수에 넣고, 변수를 servlet 컨테이너를 초기화 할 때 가져와서 사했는데
                            // 지금은 확장하고자 하는 클래스 내부에 ApplicationContext를 참조하게 해야하니까 자기 자신을 참조
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };
        // 스프링 컨테이너에 빈 등록
        applicationContext.register(applicationClass);
        // 스프링 컨테이너 초기화 작업 -> refresh()로 함
        applicationContext.refresh();
    }

}
