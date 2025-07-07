package react.casestudy.react.bookselling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import react.casestudy.react.bookselling.dao.MemberRepository;
import react.casestudy.react.bookselling.domain.Member;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public int addMember(Member member) {
        return memberRepository.addMember(member);
    }

    public boolean updateMember(int id, Member member) {
        if (memberRepository.existsById(id)) {
            memberRepository.updateMember(id, member);
            return true;
        }
        return false;
    }

    public Member getMemberById(int id) {
        return memberRepository.getMemberById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.getAllMembers();
    }

    public List<Member> searchMembersByName(String name) {
        return memberRepository.searchMembersByName(name);
    }
}

