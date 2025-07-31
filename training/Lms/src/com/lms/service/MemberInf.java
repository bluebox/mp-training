package com.lms.service;


import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;

import java.util.List;

public interface MemberInf{

        List<Member> getAllMembers();

		void validate(String name, String email, String mobile, String address, String gender)
				throws InvalidInputException;
}
