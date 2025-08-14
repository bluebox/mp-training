package com.example.SpringBootDatabaseMember.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootDatabaseMember.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Integer> {

}
