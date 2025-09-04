package com.medplus.exptracker.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.medplus.exptracker.Model.User;
import com.medplus.exptracker.Service.UserService;

@Component
public class AuthUtil {
	@Autowired
	UserService userService;
	
	public User getCurrentUser() throws UsernameNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		User user = userService.getUserByUserName(username);
		return user;
	}
}
