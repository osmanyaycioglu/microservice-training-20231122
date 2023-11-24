package com.micro.training.msagreement.services.models;

import com.micro.training.msagreement.rest.models.PhoneDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Contact {

    private Long              contactId;
    private String            contactUuid;
    private String            firstName;
    private String            lastName;
    private String            email;
    private ContactCredential contactCredential;
    private List<PhoneDto>    phoneNumbers;
    private LocalDateTime     createDate;
    private LocalDateTime     updateDate;
    private EContactStatus    contactStatus;

}
