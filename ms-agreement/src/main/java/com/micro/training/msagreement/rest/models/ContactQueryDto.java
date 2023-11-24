package com.micro.training.msagreement.rest.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class ContactQueryDto {
    private String         firstName;
    private String         lastName;
    private String         email;
    private List<PhoneDto> phoneNumbers;

}
