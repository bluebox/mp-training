package com.example.lms.service.impl;

import com.example.lms.model.Member;
import com.example.lms.repository.MemberRepository;
import com.example.lms.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public int addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public int updateMember(Member member) {
        return memberRepository.update(member);
    }

    @Override
    public int deleteMember(Long id) {
        return memberRepository.deleteById(id);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
