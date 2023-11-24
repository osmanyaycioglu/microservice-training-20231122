package com.micro.training.msagreement.rest.mappings;

import com.micro.training.msagreement.rest.models.ContactDto;
import com.micro.training.msagreement.rest.models.ContactQueryDto;
import com.micro.training.msagreement.services.models.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IContactMapping {
    IContactMapping instance = Mappers.getMapper(IContactMapping.class);

    @Mapping(source = "username",target = "contactCredential.username")
    @Mapping(source = "password",target = "contactCredential.password")
    Contact toContact(ContactDto contact);

    ContactQueryDto toContactQueryDto(Contact contact);

    List<Contact> toContacts(List<ContactDto> contact);

    List<ContactQueryDto> toContactQueryDtos(List<Contact> contact);

}
