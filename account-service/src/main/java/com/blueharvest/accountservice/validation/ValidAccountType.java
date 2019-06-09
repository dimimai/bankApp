package com.blueharvest.accountservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountTypeValidator.class)
public @interface ValidAccountType {
    String message() default "This account type is not supported";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}