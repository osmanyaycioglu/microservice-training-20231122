package com.micro.training.msagreement.services;

import com.micro.training.msagreement.data.ContactDataManager;
import com.micro.training.msagreement.data.dao.IContactDao;
import com.micro.training.msagreement.services.models.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactProvisionService {
    private final ContactDataManager contactDataManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public String addContact(Contact contactParam){
        //contactDataManager.insertContactX(contactParam);
        //contactDataManager.insertContactY(contactParam);
        return contactDataManager.insertContact(contactParam).getContactUuid();
    }

}
