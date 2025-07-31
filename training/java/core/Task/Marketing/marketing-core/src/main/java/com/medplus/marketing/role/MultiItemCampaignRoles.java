package com.medplus.marketing.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.constants.MarketingConstants;
import com.medplus.marketing.util.UserUtil;

public class MultiItemCampaignRoles implements MarketingRoles {

	@Override
	public List<Integer> getApplicableTypesByViewRoles() {
		return getRoles(MarketingConstants.MIC_PHARMACY_VIEW_ROLE, MarketingConstants.MIC_PATHLABS_VIEW_ROLE, MarketingConstants.MIC_LENS_VIEW_ROLE);
	}

	@Override
	public List<Integer> getApplicableTypesByCreateRoles() {
		return getRoles(MarketingConstants.MIC_PHARMACY_CREATE_ROLE, MarketingConstants.MIC_PATHLABS_CREATE_ROLE, MarketingConstants.MIC_LENS_CREATE_ROLE);
	}

	@Override
	public List<Integer> getApplicableTypesByEditRoles() {
		return getRoles(MarketingConstants.MIC_PHARMACY_EDIT_ROLE, MarketingConstants.MIC_PATHLABS_EDIT_ROLE, MarketingConstants.MIC_LENS_EDIT_ROLE);
	}

	@Override
	public List<Integer> getApplicableTypesByApproveRoles() {
		return getRoles(MarketingConstants.MIC_PHARMACY_APPROVE_ROLE, MarketingConstants.MIC_PATHLABS_APPROVE_ROLE, MarketingConstants.MIC_LENS_APPROVE_ROLE);
	}

	@Override
	public List<Integer> getApplicableTypesByCloseRoles() {
		return getRoles(MarketingConstants.MIC_PHARMACY_CLOSE_ROLE, MarketingConstants.MIC_PATHLABS_CLOSE_ROLE, MarketingConstants.MIC_LENS_CLOSE_ROLE);
	}

	@Override
	public List<String> getCreatedByRoles(){
		Set<String> rolesList= UserUtil.getModuleRoles();
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
