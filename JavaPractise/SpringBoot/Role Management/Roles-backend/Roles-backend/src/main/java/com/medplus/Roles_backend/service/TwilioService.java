package com.medplus.Roles_backend.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

	@Value("${twilio.accountSid}")
	private String accountSid;

	@Value("${twilio.authToken}")
	private String authToken;

	@Value("${twilio.phoneNumber}")
	private String fromNumber;

	public void sendSms(String to, String body) {
		Twilio.init(accountSid, authToken);
		Message.creator(new com.twilio.type.PhoneNumber(to), new com.twilio.type.PhoneNumber(fromNumber), body)
				.create();
	}
}
