package com.marcel.malewski.ticketsale.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<EnumConstraint, CharSequence> {
   private List<String> acceptedValues;

   @Override
   public void initialize(EnumConstraint annotation) {
      acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
              .map(Enum::name)
              .collect(Collectors.toList());
   }

   @Override
   public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
      if (value == null) {
         return true;
      }

      return acceptedValues.contains(value.toString());
   }
}
