package com.micro.training.msagreement.rest.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PhoneDto {
    @NotEmpty
    private String phoneName;
    @NotEmpty
    private String phoneNumber;

}
