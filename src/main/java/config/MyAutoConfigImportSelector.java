package config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "config.autoconfig.DispatcherServletConfig",
                "config.autoconfig.TomcatWebServerConfig"
        };
    }
}
