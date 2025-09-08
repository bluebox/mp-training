//package com.medplus.Roles_backend.security;
//
//import com.example.Roles_backend.domain.ActiveMembers;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class CustomUserDetails implements UserDetails {
//	private final ActiveMembers user;
//
//	public CustomUserDetails(ActiveMembers user) {
//		this.user = user;
//	}
//
//	public ActiveMembers getUser() {
//		return user;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		System.out.println("************************************************************************role");
//		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
//	}
//
//	@Override
//	public String getPassword() {
//		System.out.println("************************************************************************password");
//		return user.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		System.out.println("************************************************************************Username");
//		return user.getUserName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//}


