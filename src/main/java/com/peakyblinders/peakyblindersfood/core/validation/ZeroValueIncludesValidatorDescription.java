package com.peakyblinders.peakyblindersfood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;

public class ZeroValueIncludesValidatorDescription implements ConstraintValidator <ZeroValueIncludesDescription, Object> {

    private String valueField;
    private String descriptionField;
    private String descriptionRequired;

    @Override
    public void initialize(ZeroValueIncludesDescription constraint) {
            this.valueField = constraint.valueField();
            this.descriptionField = constraint.descriptionField();
            this.descriptionRequired = constraint.descriptionRequired();
    }

    @Override
    public boolean isValid(Object objectValidated, ConstraintValidatorContext constraintValidatorContext) {
        boolean validated =true;
        try {
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(objectValidated.getClass(), valueField)
                    .getReadMethod().invoke(objectValidated);

            String description = (String) BeanUtils.getPropertyDescriptor(objectValidated.getClass(), descriptionField)
                    .getReadMethod().invoke(objectValidated);

            if (value != null && BigDecimal.ZERO.compareTo(value) == 0 && description != null) {
                validated = description.toLowerCase().contains(this.descriptionRequired.toLowerCase());
            }

            return validated;
        } catch (Exception e) {
            throw new ValidationException((BindingResult) e);
        }

    }
}
