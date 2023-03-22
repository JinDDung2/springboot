package config.autoconfig;

import config.MyAutoConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfig
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
    @Bean("tomcatWebServerFactory")
    // @ConditionalMyOnClass이 먼저 통과 됨 -> 여기서 통과되지 않는다면 어차피 아래 애노테이션은 시작도 안됨
    // ConditionalMyOnClass이 웹 클래스 레벨을 통과 하면 아래 타입의 빈이 사용자가 직접 정의하지 않았다면 빈으로 등록
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
