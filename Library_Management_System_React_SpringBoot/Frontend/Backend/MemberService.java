package com.library.Library.service;

import com.library.Library.model.Member;
import com.library.Library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepo;

    public MemberService(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    public Member save(Member member) {
        return memberRepo.save(member);
    }

    public Member updateMember(Integer id, Member updatedMember) {
        Optional<Member> existingMember = memberRepo.findById(id);
        if (existingMember.isPresent()) {
            Member member = existingMember.get();
            member.setName(updatedMember.getName());
            member.setEmail(updatedMember.getEmail());
            member.setMobile(updatedMember.getMobile());
            member.setGender(updatedMember.getGender());
            member.setAddress(updatedMember.getAddress());
            return memberRepo.save(member);
        }
        return null;
    }
}
