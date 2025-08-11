package com.mycompany.Company.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.mycompany.Company.Model.Contact;

import lombok.extern.slf4j.Slf4j;
@Slf4j

@Service
@ApplicationScope
public class ContactService {
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private int counter = 0;
    
    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }


    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
    }
    
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}