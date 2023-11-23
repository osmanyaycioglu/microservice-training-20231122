package com.micro.training.msagreement.rest;

import com.micro.training.msagreement.rest.error.ErrorObj;
import com.micro.training.msagreement.rest.models.ContactDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/contact/provision")
public class ContactProvisionController {

    @PostMapping("/add")
    public String add(@Valid @RequestBody ContactDto contactDtoParam) {
        return "OK";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withErrorMessage(exp.getMessage())
                       .withErrorCode(2045)
                       .build();
    }


}
