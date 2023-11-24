package com.micro.training.msagreement.rest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data

public class PhoneDto {
    @Id
    @GeneratedValue
    private Long phoneId;
    @NotEmpty
    private String phoneName;
    @NotEmpty
    private String phoneNumber;

}
