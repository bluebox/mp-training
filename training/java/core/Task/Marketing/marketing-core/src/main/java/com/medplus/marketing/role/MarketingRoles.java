package com.medplus.marketing.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.util.UserUtil;

public interface MarketingRoles {

	List<Integer> getApplicableTypesByViewRoles();
	List<Integer> getApplicableTypesByCreateRoles();
	List<Integer> getApplicableTypesByEditRoles();
	List<Integer> getApplicableTypesByApproveRoles();
	List<Integer> getApplicableTypesByCloseRoles();
	List<String> getCreatedByRoles();
	default List<Integer> getRoles(String pharmacyRole, String pathlabsRole, String lensRole) {
		Set<String> rolesList= UserUtil.getModuleRoles();
		
		List<Integer> applicableTypes =  new ArrayList<>();
		if(UtilValidate.isEmpty(rolesList)) {
			return applicableTypes;
		}
		if(rolesList.contains(pharmacyRole)) {
			applicableTypes.add(5);
		}
		if(rolesList.contains(pathlabsRole)) {
			applicableTypes.add(6);
		}
		if(rolesList.contains(lensRole)) {
			applicableTypes.add(7);
		}
		return applicableTypes;
	}
}
