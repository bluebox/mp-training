package com.medplus.marketing.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.medplus.common.cas.model.UserDetails;
import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.constants.MarketingConstants;

public class UserUtil {
	
	private UserUtil() {}
	
	public static Optional<UserDetails> getUserDetails() {
		UserDetails userDetails = null;
		Optional<Authentication> authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if(authentication.isPresent()) {
			userDetails = (UserDetails) authentication.get().getPrincipal();
		}
		return Optional.ofNullable(userDetails);
	}
	
	public static Set<String> getModuleRoles() {
		Optional<UserDetails> userDetails = getUserDetails();
		if(userDetails.isPresent()) {
			Optional<Set<String>> moduleRoles = Optional.ofNullable(userDetails.get().getRoles());
			if(moduleRoles.isPresent()) {
				return moduleRoles.get();
			}
		}
		return Collections.emptySet();
		
	}
	
	public static Long getTenantId() {
		Optional<UserDetails> userDetails = getUserDetails();
		if(userDetails.isPresent()) {
			Optional<Long> tenantId = Optional.ofNullable(userDetails.get().getTenantId());
			if(tenantId.isPresent()) {
				return tenantId.get();
			}
		}
		return null;
	}
	
	public static String getUserId() {
		Optional<UserDetails> userDetails = getUserDetails();
		if(userDetails.isPresent()) {
			Optional<String> userId = Optional.ofNullable(userDetails.get().getUserId());
			if(userId.isPresent()) {
				return userId.get();
			}
		}
		return null;
	}

	public static List<String> getCreatedByRoles(){
		Set<String> rolesList= getModuleRoles();
		List<String> roles =  new ArrayList<>();
		if (UtilValidate.isNotEmpty(rolesList)) {
			for (String role : rolesList) {
				if (role.equals(MarketingConstants.MIC_PHARMACY_VIEW_ROLE))
					roles.add(MarketingConstants.MIC_PHARMACY_CREATE_ROLE);
				else if (role.equals(MarketingConstants.MIC_PATHLABS_VIEW_ROLE))
					roles.add(MarketingConstants.MIC_PATHLABS_CREATE_ROLE);
				else if (role.equals(MarketingConstants.MIC_LENS_VIEW_ROLE))
					roles.add(MarketingConstants.MIC_LENS_CREATE_ROLE);
				if (roles.size() == 3)
					return roles;
			}
		}
		return roles;
		
	}
}
