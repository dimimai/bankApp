package com.blueharvest.accountservice.model;

import com.blueharvest.accountservice.exception.AccountNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {
    CURRENT ,
    SAVINGS;


    private static Map<String, AccountType> namesMap = new HashMap<String, AccountType>(2);

    static {
        namesMap.put("current", CURRENT);
        namesMap.put("savings", SAVINGS);
    }

    @JsonCreator
    public static AccountType forValue(String value) {
        return namesMap.get(value.toLowerCase());
    }

}
