package com.blueharvest.accountservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {
    CURRENT,
    SAVINGS;


    private static Map<String, AccountType> namesMap = new HashMap<>(2);

    static {
        namesMap.put("current", CURRENT);
        namesMap.put("savings", SAVINGS);
    }

    @JsonCreator
    public static AccountType forValue(String value) {
        return namesMap.get(value.toLowerCase());
    }

}
