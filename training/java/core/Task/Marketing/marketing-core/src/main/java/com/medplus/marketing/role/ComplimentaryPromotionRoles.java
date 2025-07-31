package com.medplus.marketing.role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.constants.MarketingConstants;
import com.medplus.marketing.util.UserUtil;

public class ComplimentaryPromotionRoles implements MarketingRoles {
		
	@Override
	public List<Integer> getApplicableTypesByViewRoles() {
		return getRoles(MarketingConstants.COMPLIMENTARY_VIEW_ROLE);
	}

	@Override
	public List<Integer> getApplicableTypesByCreateRoles() {
		return getRoles(MarketingConstants.COMPLIMENTARY_CREATE_ROLE);
	}

	@Override
	public List<Integer> getApplicableTypesByEditRoles() {
		return getRoles(MarketingConstants.COMPLIMENTARY_EDIT_ROLE);
	}

	@Override
	public List<Integer> getApplicableTypesByApproveRoles() {
		return getRoles(MarketingConstants.COMPLIMENTARY_APPROVE_ROLE);
	}

	@Override
	public List<Integer> getApplicableTypesByCloseRoles() {
		return getRoles(MarketingConstants.COMPLIMENTARY_CLOSE_ROLE);
	}

	private List<Integer>  getRoles(String role) {
		Set<String> userRoles = UserUtil.getModuleRoles();
		if(userRoles.contains(role)) {
			return Arrays.asList(5);
		}
		return Collections.emptyList();
	}

	@Override
	public List<String> getCreatedByRoles(){
		Set<String> rolesList= UserUtil.getModuleRoles();
		List<String> roles =  new ArrayList<>();
		if (UtilValidate.isNotEmpty(rolesList) && rolesList.contains(MarketingConstants.COMPLIMENTARY_VIEW_ROLE))
			roles.add(MarketingConstants.COMPLIMENTARY_CREATE_ROLE);
		return roles;
	}

}
