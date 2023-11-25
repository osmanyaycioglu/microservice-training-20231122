package com.micro.training.msagreement.services;

import com.micro.training.msagreement.data.ContactDataManager;
import com.micro.training.msagreement.integration.ClaimIntegration;
import com.micro.training.msagreement.integration.EmailNotificationClient;
import com.micro.training.msagreement.integration.models.MessageObj;
import com.micro.training.msagreement.services.models.Contact;
import com.micro.training.msclaimapi.models.Claim;
import com.micro.training.msclaimapi.models.ClaimCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactProvisionService {
    private final ContactDataManager      contactDataManager;
    private final ClaimIntegration        claimIntegration;
    private final EmailNotificationClient emailNotificationClient;

    @Transactional(propagation = Propagation.REQUIRED)
    public String addContact(Contact contactParam) {
        ClaimCreateResponse claimLoc = claimIntegration.createClaim(new Claim("abc-1",
                                                                              BigDecimal.TEN,
                                                                              LocalDateTime.now()));
        System.out.println(claimLoc);
        //contactDataManager.insertContactX(contactParam);
        //contactDataManager.insertContactY(contactParam);
        return contactDataManager.insertContact(contactParam)
                                 .getContactUuid();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String addContact2(final Contact contactParam) {
        ClaimCreateResponse claimLoc = claimIntegration.createClaimFeign(new Claim("abc-1",
                                                                                   BigDecimal.TEN,
                                                                                   LocalDateTime.now()));
        System.out.println(claimLoc);
        emailNotificationClient.sendEmailNotfication(new MessageObj(contactParam.getEmail(),
                                                                    "abc-1 created."));
        return contactDataManager.insertContact(contactParam)
                                 .getContactUuid();

    }
}
