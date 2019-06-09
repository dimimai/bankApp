package com.blueharvest.accountservice.model.Responses;

import com.blueharvest.accountservice.model.TransactionBean;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TransactionsResponse {

    private String name;
    private String sureName;
    private Double balance;
    private List<TransactionBean> transactions;
}
