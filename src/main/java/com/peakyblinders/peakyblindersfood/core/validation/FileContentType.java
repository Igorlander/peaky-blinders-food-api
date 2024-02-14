package com.peakyblinders.peakyblindersfood.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { FileContentTypeValidator.class })
public @interface FileContentType {
    String message() default "arquivo inválido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] allowed();
}
