package com.micro.training.msagreement.rest;

import com.micro.training.msagreement.rest.models.ContactDto;
import com.micro.training.msagreement.rest.models.ContactQueryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact/query")
public class ContactQueryController {

    @GetMapping("/find/one")
    public ContactQueryDto method(@RequestParam("cid") String contactId){
        return null;
    }

}
