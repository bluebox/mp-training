package com.vardhan.main.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.vardhan.main.model.Member;

public interface MemberDao {
    
    Member save(Member member) throws DataAccessException;
    
    Optional<Member> findById(Integer memberId) throws DataAccessException;
    Optional<Member> findByMobile(String mobile) throws DataAccessException;
    Optional<Member> findByEmail(String email) throws DataAccessException;
    List<Member> findAll() throws DataAccessException;
    Optional<Member> findByIdIncludingDeleted(Integer memberId) throws DataAccessException;
    List<Member> findAllActive() throws DataAccessException;
    List<Member> findAllIncludingDeleted() throws DataAccessException;
    
    Member update(Member member) throws DataAccessException;
    
    boolean deleteById(Integer memberId) throws DataAccessException;
    boolean softDeleteById(Integer memberId) throws DataAccessException;
    boolean reactivateById(Integer memberId) throws DataAccessException;
    
    boolean existsByMobile(String mobile) throws DataAccessException;
    boolean existsByEmail(String email) throws DataAccessException;
    long count() throws DataAccessException;
    boolean existsByMobileActive(String mobile) throws DataAccessException;
    boolean existsByEmailActive(String email) throws DataAccessException;
    long countActive() throws DataAccessException;
}
