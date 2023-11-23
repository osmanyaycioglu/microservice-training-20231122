package com.micro.training.msagreement.rest.models;


import com.micro.training.msagreement.validation.BadWords;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@BadWords(value = {"abc","123","qwe"})
public class ContactDto {
    @NotEmpty(message = "firstName boş olamaz")
    @NotBlank
    @Size(min = 2,max = 15)
    private String firstName;
    @NotEmpty
    @NotBlank
    @Size(min = 3,max = 20)
    private String lastName;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    @Email
    private String email;
    @Size(min = 1)
    @Valid
    private List<PhoneDto> phoneNumbers;

}
