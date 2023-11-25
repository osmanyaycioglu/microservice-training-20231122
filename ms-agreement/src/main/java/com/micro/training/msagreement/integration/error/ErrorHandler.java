package com.micro.training.msagreement.integration.error;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(final String sParam,
                            final Response responseParam) {
        return new Exception("remote call exception");
    }

}
