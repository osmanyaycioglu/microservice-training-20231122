package com.micro.training.msagreement.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact/management")
public class ContactDataManagementController {

    @PostMapping("/update")
    public void update(){
    }
}
