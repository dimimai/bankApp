package com.blueharvest.accountservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private Date timestamp;
    private List<String> message;
    private String details;


}
