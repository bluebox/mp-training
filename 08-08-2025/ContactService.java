package com.mycompany.Company.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.Company.Model.Contact;


@Service
public class ContactService {
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact){
        log.info("Contact: {}", contact);
        return true;
    }

}