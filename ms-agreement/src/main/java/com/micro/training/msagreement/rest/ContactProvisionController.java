package com.micro.training.msagreement.rest;

import com.micro.training.msagreement.rest.mappings.IContactMapping;
import com.micro.training.msagreement.rest.models.ContactDto;
import com.micro.training.msagreement.services.ContactProvisionService;
import com.micro.training.mscommon.error.ErrorObj;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/contact/provision")
@RequiredArgsConstructor
public class ContactProvisionController {
    private final ContactProvisionService contactProvisionService;

    @PostMapping("/add")
    public String add(@Valid @RequestBody ContactDto contactDtoParam) {
        contactProvisionService.addContact(IContactMapping.instance.toContact(contactDtoParam));
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
