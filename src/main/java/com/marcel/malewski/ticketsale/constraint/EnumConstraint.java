package com.marcel.malewski.ticketsale.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumConstraint {
   Class<? extends Enum<?>> enumClass();

   String message() default "must be any of enum {enumClass}";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
