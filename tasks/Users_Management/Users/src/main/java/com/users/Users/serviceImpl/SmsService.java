package com.users.Users.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

@Service
public class SmsService {
    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @PostConstruct
    public void init() {
        if (accountSid == null || accountSid.isBlank() ||
            authToken == null || authToken.isBlank()) {
            log.error("Twilio credentials missing: check application.properties or environment variables");
            return;
        }
        Twilio.init(accountSid, authToken);
        log.info("Twilio initialized.");
    }

    public void sendSms(String toPhoneNumber, String messageBody) {
        try {
            if (toPhoneNumber == null || toPhoneNumber.isBlank()) {
                throw new IllegalArgumentException("toPhoneNumber is empty");
            }
            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    messageBody)
                .create();
            log.info("SMS sent with SID: {}", message.getSid());
        } catch (Exception e) {
            log.error("Failed to send SMS: ", e);
            throw e; 
        }
    }
}
