package dev.tulasidhar.spring_book_app.service;

import org.springframework.stereotype.Service;

import dev.tulasidhar.spring_boot_app.models.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	public void addUser(User user) {
		if(user == null || user.getName() == "") {
			log.warn("User is invalid");
		}
		else {
			log.info("User's name is "+user.getName());
		}
	}
}
