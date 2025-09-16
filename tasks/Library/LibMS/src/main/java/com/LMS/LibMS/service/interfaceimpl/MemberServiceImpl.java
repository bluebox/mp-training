package com.LMS.LibMS.service.interfaceimpl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.repository.interfaces.MemberRepository;
import com.LMS.LibMS.service.interfaces.MemberService;

import jakarta.validation.Valid;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void addMember(@Valid Member member) throws Exception {
        member.setCreatedAt(LocalDateTime.now());
        member.setCreatedBy("SYSTEM");
        try {
	         memberRepository.addMember(member);
	    } catch (Exception e) {
			if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				throw new Exception("Member already exists. Please check your details"+e.getMessage());				
			}
			
            throw new Exception("Failed to add member");
	    }

    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.getAllMembers();
    }
    
    @Override
    public Member getMemberById(Integer id) {
    	List<Member> members = memberRepository.findMembersById(List.of(id));
        if (members == null) {
        	return null;
        }
        
        return members.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMember(@Valid Member member) throws Exception {
        Member existing = memberRepository.findMembersById(List.of(member.getMemberID())).get(0);
        
        if (existing == null) {
        	throw new Exception("Member not found");        	
        }
        
        if (existing.equals(member)) {
        	throw new Exception("No changes found");        	
        }

       boolean loged = memberRepository.logMember(existing);
       
       if(!loged) {
       	throw new Exception("Member Logging Failed");
       }

        member.setUpdatedAt(LocalDateTime.now());
        member.setUpdatedBy("ADMIN");

        return memberRepository.updateMember(member);

    }
    
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMembersById(List<Integer> memberIds) throws Exception {
    
    	if (memberIds == null || memberIds.isEmpty()) 
    		return false;
    	
    	List<Member> members = memberRepository.findMembersById(memberIds);

        for (Member member : members) {
           boolean loged = memberRepository.logMember(member);
           if(!loged) {
              	throw new Exception("Member Logging Failed");
            }
        }
        
        try {
            boolean deleted = memberRepository.deleteMembersById(memberIds);
            
            if (!deleted) {
            	throw new Exception("No members deleted");            	
            }	
        } catch (Exception e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new Exception("Member is issued with a book, cannot delete");
            }
            throw new Exception("Failed to delete members");
        }
        
        return true;
    }
}