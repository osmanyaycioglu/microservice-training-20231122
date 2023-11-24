package com.micro.training.msagreement.data.dao;

import com.micro.training.msagreement.services.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactDao extends JpaRepository<Contact,Long> {
}
