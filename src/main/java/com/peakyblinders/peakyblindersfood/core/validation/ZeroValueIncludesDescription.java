package com.peakyblinders.peakyblindersfood.core.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ZeroValueIncludesValidatorDescription.class })
public @interface ZeroValueIncludesDescription {


    String message() default "descrição obrigatória inválida";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String valueField();

    String descriptionField();

    String descriptionRequired();

}
