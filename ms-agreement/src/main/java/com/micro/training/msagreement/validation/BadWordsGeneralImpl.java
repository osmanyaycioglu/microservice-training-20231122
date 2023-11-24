package com.micro.training.msagreement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BadWordsGeneralImpl implements ConstraintValidator<BadWords, Object> {
    private BadWords badWords;

    @Override
    public void initialize(final BadWords annoParam) {
        badWords = annoParam;
    }

    @Override
    public boolean isValid(final Object value,
                           final ConstraintValidatorContext context) {
        Field[] declaredFieldsLoc = value.getClass()
                                         .getDeclaredFields();
        List<String> errors = new ArrayList<>();
        for (Field declaredFieldLoc : declaredFieldsLoc) {
            if (declaredFieldLoc.getType() == String.class) {
                try {
                    declaredFieldLoc.setAccessible(true);
                    String   oLoc      = (String) declaredFieldLoc.get(value);
                    String[] valuesLoc = badWords.value();
                    if (oLoc != null) {
                        for (String valueLoc : valuesLoc) {
                            if (oLoc.contains(valueLoc)) {
                                errors.add(declaredFieldLoc.getName() + " contains bad word : " + valueLoc);
                            }
                        }
                    }
                } catch (Exception eParam) {
                    throw new RuntimeException(eParam);
                }
            }
        }
        if (!errors.isEmpty()) {
            StringBuilder builderLoc = new StringBuilder();
            for (String errorLoc : errors) {
                builderLoc.append(errorLoc)
                          .append("-");
            }
            context.disableDefaultConstraintViolation();
            ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilderLoc = context.buildConstraintViolationWithTemplate(builderLoc.toString());
            constraintViolationBuilderLoc.addConstraintViolation();
            return false;
        }
        return true;
    }
}
