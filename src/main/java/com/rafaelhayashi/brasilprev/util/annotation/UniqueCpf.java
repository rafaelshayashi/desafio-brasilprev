package com.rafaelhayashi.brasilprev.util.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCpfValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCpf {

    String message() default "Customer already exists";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
