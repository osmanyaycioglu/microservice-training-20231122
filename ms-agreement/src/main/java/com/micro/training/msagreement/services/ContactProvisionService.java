package com.micro.training.msagreement.services;

import com.micro.training.msagreement.data.ContactDataManager;
import com.micro.training.msagreement.data.dao.IContactDao;
import com.micro.training.msagreement.integration.ClaimIntegration;
import com.micro.training.msagreement.integration.models.Claim;
import com.micro.training.msagreement.integration.models.ClaimCreateResponse;
import com.micro.training.msagreement.services.models.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactProvisionService {
    private final ContactDataManager contactDataManager;
    private final ClaimIntegration claimIntegration;

    @Transactional(propagation = Propagation.REQUIRED)
    public String addContact(Contact contactParam){
        ClaimCreateResponse claimLoc = claimIntegration.createClaim(new Claim("abc-1",
                                                                              BigDecimal.TEN,
                                                                              LocalDateTime.now()));
        System.out.println(claimLoc);
        //contactDataManager.insertContactX(contactParam);
        //contactDataManager.insertContactY(contactParam);
        return contactDataManager.insertContact(contactParam).getContactUuid();
    }

}
