package com.library.utilities;

import com.library.domain.Member;

public class MemberValidation {
	   public static boolean isValidMember(Member member) {
	        if (member == null) return false;
	        if (member.getName() == null || member.getName().trim().isEmpty()) return false;
	        if (member.getEmail() == null || !member.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) return false;
	        if (String.valueOf(member.getMobile()).length() != 10) return false;
	        if (member.getGender() != 'M' && member.getGender() != 'F') return false;
	        if (member.getAddress() == null || member.getAddress().trim().isEmpty()) return false;
	        return true;
	    }


}
