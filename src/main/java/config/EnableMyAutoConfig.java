package config;

import config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DispatcherServlet.class, TomcatWebServerConfig.class}) // component 애노테이션 혹은 메타 애노테이션으로 갖고 있는 애노테이션이 붙은 클래스들을 직접 추가
public @interface EnableMyAutoConfig {
}
