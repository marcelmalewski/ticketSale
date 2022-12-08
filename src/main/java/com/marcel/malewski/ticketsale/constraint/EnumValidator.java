package com.marcel.malewski.ticketsale.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumValidator implements ConstraintValidator<EnumConstraint, String> {
   private Pattern pattern;

   @Override
   public void initialize(EnumConstraint annotation) {
      try {
         pattern = Pattern.compile(annotation.regexp());
      } catch (PatternSyntaxException e) {
         throw new IllegalArgumentException("Given regex is invalid", e);
      }
   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null) {
         return true;
      }

      Matcher m = pattern.matcher(value);
      return m.matches();
   }
}
