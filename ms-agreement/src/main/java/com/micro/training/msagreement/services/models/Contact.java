package com.micro.training.msagreement.services.models;

import com.micro.training.msagreement.rest.models.PhoneDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "contact")
@NoArgsConstructor
@SequenceGenerator(sequenceName = "contact_seq",name = "contact_seq")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "contact_seq")
    private Long              contactId;
    private String            contactUuid;
    private String            firstName;
    private String            lastName;
    private String            email;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ContactCredential contactCredential;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<PhoneDto>    phoneNumbers;
    private LocalDateTime     createDate;
    private LocalDateTime     updateDate;
    private EContactStatus    contactStatus;

    @PrePersist
    public void persist(){
        createDate = LocalDateTime.now();
        contactUuid = UUID.randomUUID().toString();
    }

    @PreUpdate
    public void update(){
        updateDate = LocalDateTime.now();
    }
}
