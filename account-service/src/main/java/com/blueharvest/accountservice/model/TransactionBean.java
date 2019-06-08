package com.blueharvest.accountservice.model;

import lombok.*;

@Data
@Builder
public class TransactionBean {

    private Long id;
    private Double amount;
    private Long accountId;
}
