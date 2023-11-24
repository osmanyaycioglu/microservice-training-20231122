package com.micro.training.msagreement.data;

import com.micro.training.msagreement.data.dao.IContactDao;
import com.micro.training.msagreement.services.models.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactDataManager {
    private final IContactDao contactDao;

    public Contact insertContact(Contact contactParam){
        return contactDao.save(contactParam);
    }

    public Contact insertContactX(Contact contactParam){
        return contactDao.save(contactParam);
    }

    // @Transactional(propagation = Propagation.NEVER)
    public Contact insertContactY(Contact contactParam){
        return contactDao.save(contactParam);
    }

}
