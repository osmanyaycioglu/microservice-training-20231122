package com.micro.training.mscommon.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(IllegalArgumentException exp) {
        if (logger.isDebugEnabled()){
            logger.debug("Exp : " + exp.getMessage());
        }

        logger.error("[ErrorAdvice][handleError]-> *Error* : " + exp.getMessage(),
                     exp);
        return ErrorObj.builder()
                       .withErrorMessage(exp.getMessage())
                       .withErrorCode(1045)
                       .build();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorObj handleError(IllegalStateException exp) {
        logger.error("[ErrorAdvice][handleError]-> *Error* : " + exp.getMessage(),
                     exp);
        return ErrorObj.builder()
                       .withErrorMessage(exp.getMessage())
                       .withErrorCode(1046)
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(MethodArgumentNotValidException exp) {
        logger.error("[ErrorAdvice][handleError]-> *Error* : " + exp.getMessage(),
                     exp);
        return ErrorObj.builder()
                       .withErrorMessage("Validation error")
                       .withErrorCode(1047)
                       .withSubErrors(exp.getAllErrors()
                                         .stream()
                                         .map(se -> ErrorObj.builder()
                                                            .withErrorMessage(se.toString())
                                                            .withErrorCode(1048)
                                                            .build())
                                         .collect(Collectors.toList()))
                       .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleError(Exception exp) {
        logger.error("[ErrorAdvice][handleError]-> *Error* : " + exp.getMessage(),
                     exp);
        if (exp instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                 .body(ErrorObj.builder()
                                               .withErrorMessage(exp.getMessage())
                                               .withErrorCode(2000)
                                               .build());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorObj.builder()
                                           .withErrorMessage(exp.getMessage())
                                           .withErrorCode(5000)
                                           .build());
    }
}
