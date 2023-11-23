package com.micro.training.msagreement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class BadWordsImpl implements ConstraintValidator<BadWords, String> {
    private BadWords badWords;

    @Override
    public void initialize(final BadWords annoParam) {
        badWords = annoParam;
    }

    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        return Arrays.stream(badWords.value())
                     .noneMatch(value::contains);
    }
}
