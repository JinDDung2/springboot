package config.autoconfig;

import config.MyAutoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@MyAutoConfig
public class DispatcherServletConfig {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();}
}
