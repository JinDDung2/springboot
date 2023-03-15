package config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

//    @Override
//    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        Iterable<String> candidates = ImportCandidates.load(MyAutoConfig.class, classLoader);
//        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
//    };

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();
        ImportCandidates.load(MyAutoConfig.class, classLoader).forEach(candidate ->
                autoConfigs.add(candidate)
        );

        return autoConfigs.stream().toArray(String[]::new);
    };
}
