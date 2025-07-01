package com.app.springDemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.springDemo.controller.MemberController;
import com.app.springDemo.model.Member;

@Service
public class MemberService {

	Logger log =LoggerFactory.getLogger(MemberController.class.getName());

	   public boolean saveMemberDetails(Member member){
	        boolean isSaved = true;
	        log.info(member.toString());
	        return isSaved;
	   }
}
