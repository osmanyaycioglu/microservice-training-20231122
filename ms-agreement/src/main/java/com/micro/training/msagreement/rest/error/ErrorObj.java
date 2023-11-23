package com.micro.training.msagreement.rest.error;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String boundedContext;
    private String microservice;
    private String errorMessage;
    private Integer errorCode;

}
