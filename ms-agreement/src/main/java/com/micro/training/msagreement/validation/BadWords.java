package com.micro.training.msagreement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {BadWordsImpl.class })
public @interface BadWords {
    String[] value();

    String message() default "{jakarta.validation.constraints.BadWords.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
