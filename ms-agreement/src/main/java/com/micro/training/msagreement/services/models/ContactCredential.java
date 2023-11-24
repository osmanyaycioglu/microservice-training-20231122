package com.micro.training.msagreement.services.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ContactCredential {
    @Id
    @GeneratedValue
    private Long ccId;
    private String username;
    private String password;
}
