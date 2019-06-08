package com.blueharvest.accountservice;

import com.blueharvest.accountservice.model.TransactionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="transaction-service",url = "localhost:8100")
public interface TransactionServiceProxy {

    @PostMapping(value = "/transactions",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> transactionCreation(@RequestBody TransactionBean transaction);
}
