package com.vardhan.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.vardhan.main.model.Member;

public interface MemberService {
    
    Member saveMember(Member member) throws DataAccessException;
    
    Optional<Member> findMemberById(Integer memberId) throws DataAccessException;
    Optional<Member> findMemberByMobile(String mobile) throws DataAccessException;
    Optional<Member> findMemberByEmail(String email) throws DataAccessException;
    List<Member> getAllMembers() throws DataAccessException;
    List<Member> getActiveMembers() throws DataAccessException;
    Optional<Member> findMemberByIdIncludingDeleted(Integer memberId) throws DataAccessException;
    
    Member updateMember(Member member) throws DataAccessException;
    
    boolean deleteMember(Integer memberId) throws DataAccessException;
    boolean deactivateMember(Integer memberId) throws DataAccessException;
    boolean reactivateMember(Integer memberId) throws DataAccessException;
    
    boolean isMobileExists(String mobile) throws DataAccessException;
    boolean isEmailExists(String email) throws DataAccessException;
    boolean isMobileExistsActive(String mobile) throws DataAccessException;
    boolean isEmailExistsActive(String email) throws DataAccessException;
    
    long getTotalMembersCount() throws DataAccessException;
    long getActiveMembersCount() throws DataAccessException;
}
