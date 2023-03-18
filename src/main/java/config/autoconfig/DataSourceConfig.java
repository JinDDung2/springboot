package config.autoconfig;

import config.MyAutoConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@MyAutoConfig
@ConditionalOnClass(name = "org.springframework.jdbc.core.JdbcOperations")
public class DataSourceConfig {
    @Bean
    DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        return dataSource;
    }
}
