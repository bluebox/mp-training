package com.medplus.Roles_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medplus.Roles_backend.dao.RoleDao;
import com.medplus.Roles_backend.dao.UserDao;
import com.medplus.Roles_backend.domain.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Spring Auto calling.....********");
		User dbUser = userDao.findByUsername(username);

		if (dbUser == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		List<String> roles = roleDao.getAssignedActiveRoles(dbUser.getId());
		dbUser.setRoles(roles);

		return org.springframework.security.core.userdetails.User.withUsername(dbUser.getUsername())
				.password(dbUser.getPassword()).roles(roles.toArray(new String[0])).build();
	}
}
