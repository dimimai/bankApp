package com.blueharvest.accountservice.validation;

import com.blueharvest.accountservice.model.AccountType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AccountTypeValidator implements ConstraintValidator<ValidAccountType, AccountType> {

    public void initialize(ValidAccountType constraint) {
    }

    @Override
    public boolean isValid(AccountType value, ConstraintValidatorContext context) {
        List<String> values = Arrays.asList(AccountType.CURRENT.toString(),AccountType.SAVINGS.toString());

        return values.contains(value.toString());
    }
}
