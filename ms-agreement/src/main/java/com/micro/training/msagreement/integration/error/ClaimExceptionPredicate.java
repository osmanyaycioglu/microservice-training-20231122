package com.micro.training.msagreement.integration.error;

import org.springframework.web.client.RestClientResponseException;

import java.util.function.Predicate;

public class ClaimExceptionPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(final Throwable throwableParam) {
        if (throwableParam instanceof RestClientResponseException){
            RestClientResponseException rce = (RestClientResponseException) throwableParam;
            if (rce.getStatusCode().is4xxClientError()){
                return true;
            }
         }
        return true;
    }

}
