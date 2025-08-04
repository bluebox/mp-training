package library.service;

import java.util.List;

import library.dao.interfaceimpl.MemberDAOImpl;
import library.dao.interfaces.MemberDAO;
import library.exception.LibraryException;
import library.model.Member;
import library.service.interfaces.MemberService;
import library.validation.MemberValidator;

public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;

    public MemberServiceImpl() {
        this.memberDAO = new MemberDAOImpl();
    }

    @Override
    public String addMember(Member member) {
        MemberValidator.validateMember(member);

        Member emailCriteria = new Member();
        emailCriteria.setEmail(member.getEmail());
        if (!memberDAO.findMembers(emailCriteria).isEmpty()) {
            throw new LibraryException("Member with email " + member.getEmail() + " already exists.");
        }

        Member phoneCriteria = new Member();
        phoneCriteria.setPhoneNumber(member.getPhoneNumber());
        if (!memberDAO.findMembers(phoneCriteria).isEmpty()) {
            throw new LibraryException("Member with phone number " + member.getPhoneNumber() + " already exists.");
        }

        return memberDAO.addMember(member);
    }

    @Override
    public Member getMemberById(int id) {
        MemberValidator.validateMemberId(id);

        Member criteria = new Member();
        criteria.setMemberID(id);
        List<Member> members = memberDAO.findMembers(criteria);
        return members.isEmpty() ? null : members.get(0);
    }

    @Override
    public Member getMemberByEmail(String email) {
        MemberValidator.validateMemberEmail(email);

        Member criteria = new Member();
        criteria.setEmail(email);
        List<Member> members = memberDAO.findMembers(criteria);
        return members.isEmpty() ? null : members.get(0);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDAO.findMembers(new Member());
    }

    @Override
    public List<Member> findMembers(Member criteria) {
        if (criteria == null) {
            throw new LibraryException("Search criteria cannot be null.");
        }
        return memberDAO.findMembers(criteria);
    }

    @Override
    public void updateMember(Member member) {
        MemberValidator.validateMemberForUpdate(member);

        Member existingMember = getMemberById(member.getMemberID());
        if (existingMember == null) {
            throw new LibraryException("Member with ID " + member.getMemberID() + " not found.");
        }

        if (!existingMember.getEmail().equalsIgnoreCase(member.getEmail())) {
             Member emailCriteria = new Member();
             emailCriteria.setEmail(member.getEmail());
             if (!memberDAO.findMembers(emailCriteria).isEmpty()) {
                throw new LibraryException("Another member with email " + member.getEmail() + " already exists.");
            }
        }

        if (existingMember.getPhoneNumber() != member.getPhoneNumber()) {
            Member phoneCriteria = new Member();
            phoneCriteria.setPhoneNumber(member.getPhoneNumber());
            if (!memberDAO.findMembers(phoneCriteria).isEmpty()) {
                throw new LibraryException("Another member with phone number " + member.getPhoneNumber() + " already exists.");
            }
        }

        memberDAO.updateMember(member);
    }

    @Override
    public void deleteMember(Member member) {
        if (member == null || member.getMemberID() <= 0) { 
            throw new LibraryException("Invalid member data for deletion.");
        }
        
        Member existingMember = getMemberById(member.getMemberID());
        if (existingMember == null) {
            throw new LibraryException("Member with ID " + member.getMemberID() + " not found.");
        }
        memberDAO.deleteMember(member);
    }

    @Override
    public void deleteMembers(List<Integer> memberIds) {
        if (memberIds == null || memberIds.isEmpty()) {
            throw new LibraryException("No member IDs provided for batch deletion.");
        }

        memberDAO.deleteMembersInBatch(memberIds);
    }
}