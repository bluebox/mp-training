package com.medplus.marketing.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.constants.MarketingConstants;
import com.medplus.popup.constants.ApplicableType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PopUpUtil {

	private List<ApplicableType> getApplicableTypesAgainstUserRoles(Set<String> userRoles, String labRole,String pharmaRole, List<ApplicableType> selectedApplicableTypes) {
		List<ApplicableType> applicableTypes = new ArrayList<>();
		if(UtilValidate.isNotEmpty(userRoles)) {
			if(userRoles.contains(labRole) && !userRoles.contains(pharmaRole)) {
				applicableTypes.add(ApplicableType.PATHLABS);
			} else if(userRoles.contains(pharmaRole) && !userRoles.contains(labRole)) {
				applicableTypes.add(ApplicableType.PHARMACY);
			}
		}
		return UtilValidate.isNotEmpty(applicableTypes) ? applicableTypes : selectedApplicableTypes;
	}

	public List<ApplicableType> getApplicableTypesAgainstUserRolesForConfigView(
			List<ApplicableType> selectedApplicableTypes) {
		Set<String> userRoles = UserUtil.getModuleRoles();
		return getApplicableTypesAgainstUserRoles(userRoles, MarketingConstants.LAB_CONFIG_VIEW_ROLE, MarketingConstants.PHARMA_CONFIG_VIEW_ROLE, selectedApplicableTypes);
	}

	public List<ApplicableType> getApplicableTypesAgainstUserRolesForTemplateView(
			List<ApplicableType> selectedApplicableTypes) {
		Set<String> userRoles = UserUtil.getModuleRoles();
		return getApplicableTypesAgainstUserRoles(userRoles, MarketingConstants.LAB_TEMPLATE_VIEW_ROLE, MarketingConstants.PHARMA_TEMPLATE_VIEW_ROLE, selectedApplicableTypes);
	}
}
