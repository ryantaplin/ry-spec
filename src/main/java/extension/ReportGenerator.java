package extension;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@ExtendWith(ReportExtension.class)
public @interface ReportGenerator {

    //TODO: use reflection to access these fields...
    boolean loggingEnabled() default false;
}


