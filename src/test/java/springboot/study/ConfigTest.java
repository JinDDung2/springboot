package springboot.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigTest {
    @Test
    void configuration1() {
        MyConfiguration myConfig = new MyConfiguration();
        BeanA beanA = myConfig.beanA();
        BeanB beanB = myConfig.beanB();
        Assertions.assertThat(beanA.common).isNotSameAs(beanB.common);
    }

    @Test
    void configuration2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfiguration.class);
        ac.refresh();

        BeanA beanA = ac.getBean(BeanA.class);
        BeanB beanB = ac.getBean(BeanB.class);

        Assertions.assertThat(beanA.common).isSameAs(beanB.common);
    }

    @Test
    void proxyCommonMethod() {
        MyConfigurationProxy myConfigurationProxy = new MyConfigurationProxy();

        BeanA beanA = myConfigurationProxy.beanA();
        BeanB beanB = myConfigurationProxy.beanB();

        Assertions.assertThat(beanA.common).isSameAs(beanB.common);
    }

    // 프록시 작동 방식
    static class MyConfigurationProxy extends MyConfiguration{
        private Common common;

        @Override
        Common common() {
            if (this.common == null) {
                this.common = super.common();
            }

            return this.common;
        }
    }

    @Configuration
    static class MyConfiguration {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        BeanA beanA() {
            return new BeanA(common());
        }

        @Bean
        BeanB beanB() {
            return new BeanB(common());
        }
    }

    static class BeanA {
        private final Common common;

        public BeanA(Common common) {
            this.common = common;
        }
    }

    static class BeanB {
        private final Common common;

        public BeanB(Common common) {
            this.common = common;
        }
    }

    private static class Common {
    }
}
