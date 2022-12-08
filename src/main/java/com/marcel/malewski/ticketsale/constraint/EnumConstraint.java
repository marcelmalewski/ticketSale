package com.marcel.malewski.ticketsale.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumConstraint {
   String regexp();

   String message() default "Invalid value";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
